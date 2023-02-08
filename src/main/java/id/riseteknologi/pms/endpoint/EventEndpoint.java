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
import id.riseteknologi.pms.model.Supplier;
import id.riseteknologi.pms.model.Warehouse;
import id.riseteknologi.pms.repository.SupplierRepository;
import id.riseteknologi.pms.repository.WarehouseRepository;
import id.riseteknologi.pms.rule.input.model.Product;
import id.riseteknologi.pms.rule.input.model.SupplierChecker;
import id.riseteknologi.pms.rule.input.model.SupplierRise;
import id.riseteknologi.pms.rule.input.model.Transaction;
import id.riseteknologi.pms.rule.output.model.PurchaseDecision;
import id.riseteknologi.pms.rule.output.model.TransactionDecision;
import id.riseteknologi.pms.rules.PurchaseUnit;
import id.riseteknologi.pms.rules.PurchaseUnitV2;
import id.riseteknologi.pms.rules.TransactionUnit;
import io.quarkus.logging.Log;

@Path("event")
public class EventEndpoint {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  SupplierRepository supplierRepository;

  @Inject
  WarehouseMapper warehouseMapper;

  private TransactionUnit transactionUnit;
  private RuleUnitInstance<TransactionUnit> transactionUnitInstance;

  private PurchaseUnit purchaseUnit;
  private RuleUnitInstance<PurchaseUnit> purchaseUnitInstance;

  private PurchaseUnitV2 purchaseUnitV2;
  private RuleUnitInstance<PurchaseUnitV2> purchaseUnitV2Instance;

  private void createPurchaseUnit() {
    purchaseUnit = new PurchaseUnit();
    purchaseUnitInstance = RuleUnitProvider.get().createRuleUnitInstance(purchaseUnit);
  }

  private void createPurchaseUnitV2() {
    purchaseUnitV2 = new PurchaseUnitV2();
    try {
      purchaseUnitV2Instance = RuleUnitProvider.get().createRuleUnitInstance(purchaseUnitV2);
    } catch (Exception e) {
      if (purchaseUnitV2Instance == null) {
        Log.info("PURCHASE UNIT V2 INSTANCE NULL");
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

    Supplier rise = supplierRepository.getSupplierByName("rise");
    List<Warehouse> riseWarehouse =
        warehouseRepository.getWarehouseBySupplierProductId(rise.getId(), product.getId());
    Long totalRiseStock = riseWarehouse.stream().mapToLong(w -> w.getStock()).sum();
    BigDecimal sumRisePrice = new BigDecimal(
        riseWarehouse.stream().mapToDouble(w -> w.getStock() * w.getPrice().doubleValue()).sum());
    Log.info("sumRisePrice: " + sumRisePrice);
    Log.info("totalRiseStock: " + totalRiseStock);
    BigDecimal averageRisePrice = BigDecimal.ZERO;
    if (totalRiseStock != 0) {
      averageRisePrice =
          sumRisePrice.divide(new BigDecimal(totalRiseStock), MathContext.DECIMAL128);
    }

    id.riseteknologi.pms.rule.input.model.Supplier supplierRise =
        new id.riseteknologi.pms.rule.input.model.Supplier(rise.getId(), rise.getName(),
            averageRisePrice, totalRiseStock);
    purchaseUnit.getRise().set(supplierRise);
    Log.info("RISE PRICE: " + supplierRise.getPrice());
    Log.info("RISE STOCK: " + supplierRise.getStock());

    List<WarehouseDTO> previousWarehouses = purchaseEvent.getPreviousWarehouses();
    if (previousWarehouses == null) {
      previousWarehouses = new ArrayList<>();
    }
    Map<UUID, WarehouseDTO> previousWarehousesMap = new HashMap<>();
    for (WarehouseDTO warehouseDTO : previousWarehouses) {
      previousWarehousesMap.put(warehouseDTO.getSupplierId(), warehouseDTO);
    }

    List<Supplier> suppliers = supplierRepository.getAllExceptRise();
    for (Supplier supplier : suppliers) {
      List<Warehouse> warehouses =
          warehouseRepository.getWarehouseBySupplierProductId(supplier.getId(), product.getId());
      for (Warehouse warehouse : warehouses) {
        purchaseUnit.getSuppliers().add(warehouseMapper.toSupplierPriceChanged(warehouse,
            previousWarehousesMap.get(supplier.getId())));
        purchaseUnit.getSupplierCheckers().add(new SupplierChecker(supplier.getId()));
      }
    }

    purchaseUnitInstance.fire();
    return purchaseUnit.getPurchaseDecision();
  }

  // If day changed or maybe every several hours check supplier
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("purchase-v2")
  public PurchaseDecision decidePurchaseEventV2(PurchaseEvent purchaseEvent) {
    createPurchaseUnitV2();
    Product product = new Product(purchaseEvent.getProductId());
    purchaseUnitV2.getProduct().set(product);

    Supplier rise = supplierRepository.getSupplierByName("rise");
    List<Warehouse> riseWarehouse =
        warehouseRepository.getWarehouseBySupplierProductId(rise.getId(), product.getId());
    Long totalRiseStock = riseWarehouse.stream().mapToLong(w -> w.getStock()).sum();
    BigDecimal sumRisePrice = new BigDecimal(
        riseWarehouse.stream().mapToDouble(w -> w.getStock() * w.getPrice().doubleValue()).sum());
    BigDecimal averageRisePrice = BigDecimal.ZERO;
    if (totalRiseStock != 0) {
      averageRisePrice =
          sumRisePrice.divide(new BigDecimal(totalRiseStock), MathContext.DECIMAL128);
    }

    id.riseteknologi.pms.rule.input.model.Supplier supplierRise =
        new id.riseteknologi.pms.rule.input.model.Supplier(rise.getId(), rise.getName(),
            averageRisePrice, totalRiseStock);
    purchaseUnitV2.getRise().set(supplierRise);

    List<Supplier> suppliers = supplierRepository.getAllExceptRise();
    List<id.riseteknologi.pms.rule.input.model.Supplier> currentSuppliers = new ArrayList<>();
    for (Supplier supplier : suppliers) {
      List<Warehouse> warehouses =
          warehouseRepository.getWarehouseBySupplierProductId(supplier.getId(), product.getId());
      for (Warehouse warehouse : warehouses) {
        currentSuppliers.add(warehouseMapper.toSupplierInput(warehouse));
      }
    }

    List<id.riseteknologi.pms.rule.input.model.Supplier> previousSuppliers = null;
    List<WarehouseDTO> previousWarehouses = purchaseEvent.getPreviousWarehouses();
    if (previousWarehouses == null || previousWarehouses.isEmpty()) {
      previousSuppliers = new ArrayList<>(currentSuppliers);
    } else {
      previousSuppliers = previousWarehouses.stream().map(w -> warehouseMapper.toSupplierInput(w))
          .collect(Collectors.toList());
    }

    Log.info(previousWarehouses);
    Log.info(previousSuppliers);
    for (id.riseteknologi.pms.rule.input.model.Supplier supplier : previousSuppliers) {
      purchaseUnitV2.getPreviousSuppliers().add(supplier);
    }
    for (id.riseteknologi.pms.rule.input.model.Supplier supplier : currentSuppliers) {
      purchaseUnitV2.getCurrentSuppliers().add(supplier);
      purchaseUnitV2.getSupplierCheckers().add(new SupplierChecker(supplier.getId(), false));
    }

    purchaseUnitV2Instance.fire();
    return purchaseUnitV2.getPurchaseDecision();
  }

  // When there is incoming transaction from client
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("transaction")
  public TransactionDecision decideTransactionEvent(TransactionEvent transactionEvent) {
    createTransactionUnit();
    Transaction transaction = new Transaction(transactionEvent.getProductId());
    transactionUnit.getTransaction().set(transaction);

    Supplier rise = supplierRepository.getSupplierByName("rise");
    List<Warehouse> riseWarehouse = warehouseRepository
        .getWarehouseBySupplierProductId(rise.getId(), transaction.getProductId());
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
}
