package id.riseteknologi.pms.rules;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.dmn.endpoint.DMNInputTest;
import id.riseteknologi.pms.dmn.model.BuyDecision;
import id.riseteknologi.pms.dmn.model.DecisionInput;
import id.riseteknologi.pms.rule.model.Product;
import id.riseteknologi.pms.rule.model.PurchaseResult;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.SupplierPriceChanged;

public class PurchaseUnit implements RuleUnitData {

  private Long maxBuy;
  private Product product;
  private SingletonStore<Supplier> rise;
  private DataStore<SupplierPriceChanged> suppliers;
  private PurchaseResult purchaseResult;

  public PurchaseUnit() {
    this(100L, null, DataSource.createSingleton(), DataSource.createStore(), new PurchaseResult());
  }

  public PurchaseUnit(Long maxBuy, Product product, SingletonStore<Supplier> rise,
      DataStore<SupplierPriceChanged> suppliers, PurchaseResult purchaseResult) {
    this.maxBuy = maxBuy;
    this.product = product;
    this.rise = rise;
    this.suppliers = suppliers;
    this.purchaseResult = purchaseResult;
  }

  public void buyFromBoth(SupplierPriceChanged alto, SupplierPriceChanged spi) {
    id.riseteknologi.pms.dmn.model.Supplier altoSupplier =
        new id.riseteknologi.pms.dmn.model.Supplier(alto.getCurrentPrice(), alto.getStock());
    id.riseteknologi.pms.dmn.model.Supplier spiSupplier =
        new id.riseteknologi.pms.dmn.model.Supplier(spi.getCurrentPrice(), spi.getStock());
    DecisionInput decisionInput = new DecisionInput(altoSupplier, spiSupplier, maxBuy);
    BuyDecision buyDecision = new DMNInputTest().getBuyDecisionResult(decisionInput);
    purchaseResult.setBuy(true);
    purchaseResult.setAlto(true);
    purchaseResult.setSpi(true);
    purchaseResult.setAltoCount(buyDecision.getAlto());
    purchaseResult.setSpiCount(buyDecision.getSpi());
    purchaseResult.setTotalBuyCount(buyDecision.getTotalBought());
  }

  public void buyFromOne(SupplierPriceChanged supplier) {
    purchaseResult.setBuy(true);
    Long buyCount = Math.min(maxBuy, supplier.getStock());
    if (supplier.getName().equals("alto")) {
      purchaseResult.setAlto(true);
      purchaseResult.setSpi(false);
      purchaseResult.setAltoCount(buyCount);
    } else {
      purchaseResult.setAlto(false);
      purchaseResult.setSpi(true);
      purchaseResult.setSpiCount(buyCount);
    }
  }

  public Long getMaxBuy() {
    return maxBuy;
  }

  public Product getProduct() {
    return product;
  }

  public PurchaseResult getPurchaseResult() {
    return purchaseResult;
  }

  public SingletonStore<Supplier> getRise() {
    return rise;
  }

  public DataStore<SupplierPriceChanged> getSuppliers() {
    return suppliers;
  }

  public void resetPurchaseUnit() {
    this.maxBuy = 100L;
    this.product = null;
    this.rise.clear();
    this.suppliers = DataSource.createStore();
    this.purchaseResult = new PurchaseResult();
  }

  public void setMaxBuy(Long maxBuy) {
    this.maxBuy = maxBuy;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setPurchaseResult(PurchaseResult purchaseResult) {
    this.purchaseResult = purchaseResult;
  }

  public void setRise(SingletonStore<Supplier> rise) {
    this.rise = rise;
  }

  public void setSuppliers(DataStore<SupplierPriceChanged> suppliers) {
    this.suppliers = suppliers;
  }

}
