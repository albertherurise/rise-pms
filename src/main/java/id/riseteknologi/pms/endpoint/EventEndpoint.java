package id.riseteknologi.pms.endpoint;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import id.riseteknologi.pms.dto.WarehouseDTO;
import id.riseteknologi.pms.event.PurchaseEvent;
import id.riseteknologi.pms.event.TransactionEvent;
import id.riseteknologi.pms.mapper.WarehouseMapper;
import id.riseteknologi.pms.model.RiseWarehouse;
import id.riseteknologi.pms.model.Supplier;
import id.riseteknologi.pms.model.Warehouse;
import id.riseteknologi.pms.model.Whitelist;
import id.riseteknologi.pms.repository.BlacklistRepository;
import id.riseteknologi.pms.repository.RiseWarehouseRepository;
import id.riseteknologi.pms.repository.SupplierRepository;
import id.riseteknologi.pms.repository.WarehouseRepository;
import id.riseteknologi.pms.repository.WhitelistRepository;
import id.riseteknologi.pms.rule.input.model.Product;
import id.riseteknologi.pms.rule.input.model.SupplierChecker;
import id.riseteknologi.pms.rule.input.model.SupplierRise;
import id.riseteknologi.pms.rule.input.model.Transaction;
import id.riseteknologi.pms.rule.output.model.PurchaseDecision;
import id.riseteknologi.pms.rule.output.model.TransactionDecision;
import id.riseteknologi.pms.rules.PurchaseUnit;
import id.riseteknologi.pms.rules.TransactionUnit;
import io.quarkus.logging.Log;

@Path("event")
public class EventEndpoint {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  RiseWarehouseRepository riseWarehouseRepository;

  @Inject
  SupplierRepository supplierRepository;

  @Inject
  WhitelistRepository whitelistRepository;

  @Inject
  BlacklistRepository blacklistRepository;

  @Inject
  WarehouseMapper warehouseMapper;

  private TransactionUnit transactionUnit;
  private RuleUnitInstance<TransactionUnit> transactionUnitInstance;


  private PurchaseUnit purchaseUnit;
  private RuleUnitInstance<PurchaseUnit> purchaseUnitInstance;

  private void createPurchaseUnit() {
    purchaseUnit = new PurchaseUnit();
    try {
      purchaseUnitInstance = RuleUnitProvider.get().createRuleUnitInstance(purchaseUnit);
    } catch (Exception e) {
      Log.error("CANNOT CREATE PURCHASE UNIT");
      if (purchaseUnitInstance == null) {
        Log.info("PURCHASE UNIT INSTANCE NULL");
      }
    }
  }

  private void createTransactionUnit() {
    transactionUnit = new TransactionUnit();
    transactionUnitInstance = RuleUnitProvider.get().createRuleUnitInstance(transactionUnit);
  }


  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("purchase")
  public PurchaseDecision decidePurchaseEvent(PurchaseEvent purchaseEvent) {
    createPurchaseUnit();
    Product product = new Product(purchaseEvent.getProductId());
    purchaseUnit.getProduct().set(product);

    List<RiseWarehouse> riseWarehouse =
        riseWarehouseRepository.getWarehouseByProductId(product.getId());
    Long totalRiseStock = riseWarehouse.stream().mapToLong(w -> w.getStock()).sum();
    BigDecimal sumRisePrice = new BigDecimal(
        riseWarehouse.stream().mapToDouble(w -> w.getStock() * w.getPrice().doubleValue()).sum());
    BigDecimal averageRisePrice = BigDecimal.ZERO;
    if (totalRiseStock != 0) {
      averageRisePrice =
          sumRisePrice.divide(new BigDecimal(totalRiseStock), MathContext.DECIMAL128);
    }
    Log.info("AVERAGE RISE PRICE: " + averageRisePrice);
    Log.info("TOTAL RISE STOCK: " + totalRiseStock);
    Supplier rise = supplierRepository.getSupplierByName("rise");
    SupplierRise supplierRise =
        new SupplierRise(rise.getId(), rise.getName(), averageRisePrice, totalRiseStock);
    purchaseUnit.getRise().set(supplierRise);

    List<Supplier> suppliers = supplierRepository.getAllExceptRise();
    List<id.riseteknologi.pms.rule.input.model.Supplier> previousSuppliers = new ArrayList<>();
    for (Supplier supplier : suppliers) {
      List<Warehouse> warehouses =
          warehouseRepository.getWarehouseBySupplierProductId(supplier.getId(), product.getId());
      for (Warehouse warehouse : warehouses) {
        previousSuppliers.add(warehouseMapper.toSupplierInput(warehouse));
      }
    }

    List<id.riseteknologi.pms.rule.input.model.Supplier> currentSuppliers = null;
    List<WarehouseDTO> currentWarehouses = purchaseEvent.getCurrentWarehouses();
    if (currentWarehouses == null || currentWarehouses.isEmpty()) {
      currentSuppliers = new ArrayList<>(previousSuppliers);
    } else {
      currentSuppliers = currentWarehouses.stream().map(w -> warehouseMapper.toSupplierInput(w))
          .collect(Collectors.toList());
      Map<UUID, id.riseteknologi.pms.rule.input.model.Supplier> checkCurrentSuppliers =
          new HashMap<>();
      for (id.riseteknologi.pms.rule.input.model.Supplier supplier : currentSuppliers) {
        checkCurrentSuppliers.put(supplier.getId(), supplier);
      }
      for (id.riseteknologi.pms.rule.input.model.Supplier supplier : previousSuppliers) {
        if (checkCurrentSuppliers.get(supplier.getId()) == null) {
          currentSuppliers.add(supplier);
        }
      }
    }

    Log.info(currentWarehouses);
    Log.info(currentSuppliers);
    for (id.riseteknologi.pms.rule.input.model.Supplier supplier : previousSuppliers) {
      purchaseUnit.getPreviousSuppliers().add(supplier);
    }
    for (id.riseteknologi.pms.rule.input.model.Supplier supplier : currentSuppliers) {
      purchaseUnit.getCurrentSuppliers().add(supplier);
      purchaseUnit.getSupplierCheckers().add(new SupplierChecker(supplier.getId(), false));
    }

    purchaseUnitInstance.fire();
    return purchaseUnit.getPurchaseDecision();
  }

  // When there is incoming transaction from client
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("transaction")
  public TransactionDecision decideTransactionEvent(TransactionEvent transactionEvent) {
    // Starting blacklist
    TransactionDecision transactionDecisionBlacklist =
        processBlacklist(transactionEvent.getCustomerId(), transactionEvent.getProductId());
    if (transactionDecisionBlacklist != null) {
      return transactionDecisionBlacklist;
    }

    // Starting whitelist
    TransactionDecision transactionDecisionWhitelist =
        processWhitelist(transactionEvent.getClientId(), transactionEvent.getProductId());
    if (transactionDecisionWhitelist != null) {
      return transactionDecisionWhitelist;
    }

    // Starting rule engine
    Log.info("Using rule engine to determine TransactionDecision");
    createTransactionUnit();
    Transaction transaction = new Transaction(transactionEvent.getProductId());
    transactionUnit.getTransaction().set(transaction);

    // Supplier rise = supplierRepository.getSupplierByName("rise");
    List<RiseWarehouse> riseWarehouse =
        riseWarehouseRepository.getWarehouseByProductId(transaction.getProductId());
    Long totalRiseStock = riseWarehouse.stream().mapToLong(w -> w.getStock()).sum();
    SupplierRise supplierRise = new SupplierRise(totalRiseStock);
    transactionUnit.getRise().set(supplierRise);

    List<Supplier> suppliers = supplierRepository.getAllExceptRise();
    for (Supplier supplier : suppliers) {
      List<Warehouse> warehouses = warehouseRepository
          .getWarehouseBySupplierProductId(supplier.getId(), transaction.getProductId());
      for (Warehouse warehouse : warehouses) {
        transactionUnit.getSuppliers().add(warehouseMapper.toSupplierInput(warehouse));
      }
    }

    transactionUnitInstance.fire();
    return transactionUnit.getTransactionDecision();
  }

  private TransactionDecision processBlacklist(UUID customerId, UUID productId) {
    if (blacklistRepository.isBlacklisted(customerId)) {
      Log.info(customerId + " blacklisted");
      return new TransactionDecision(productId, true, false, null);
    }
    return null;
  }

  private TransactionDecision processWhitelist(UUID clientId, UUID productId) {
    List<Whitelist> whitelists =
        whitelistRepository.getWhitelistByClientProduct(clientId, productId);
    if (whitelists == null || whitelists.isEmpty()) {
      return null;
    }
    for (Whitelist whitelist : whitelists) {
      List<Warehouse> warehouses = warehouseRepository
          .getWarehouseBySupplierProductId(whitelist.getSupplier().getId(), productId);
      for (Warehouse warehouse : warehouses) {
        Log.info(clientId + " " + productId + " whitelisted");
        return new TransactionDecision(productId, false, true, warehouse.getSupplier().getId());
      }
    }
    return null;
  }
}
