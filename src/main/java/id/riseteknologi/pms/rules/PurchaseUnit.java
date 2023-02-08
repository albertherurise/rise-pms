package id.riseteknologi.pms.rules;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.rule.input.model.Product;
import id.riseteknologi.pms.rule.input.model.Supplier;
import id.riseteknologi.pms.rule.input.model.SupplierChecker;
import id.riseteknologi.pms.rule.input.model.SupplierPriceChanged;
import id.riseteknologi.pms.rule.model.BigDecimalWrapper;
import id.riseteknologi.pms.rule.output.model.PurchaseDecision;

public class PurchaseUnit implements RuleUnitData {

  public static Boolean isLessThan(BigDecimal currentPrice, BigDecimalWrapper maxPrice) {
    return currentPrice.compareTo(maxPrice.getValue()) <= 0;
  }

  public static Boolean isLessThanAgain(BigDecimal currentPrice, BigDecimal risePrice) {
    return currentPrice.compareTo(risePrice) < 0;
  }

  public static Boolean isLessThanPriceThreshold(BigDecimal currentPrice, BigDecimal risePrice,
      BigDecimal priceThreshold) {
    return currentPrice.compareTo(priceThreshold.multiply(risePrice)) <= 0;
  }

  private Long maxBuy;
  private BigDecimal priceThreshold;
  private SingletonStore<Product> product;
  private SingletonStore<Supplier> rise;
  private DataStore<SupplierPriceChanged> suppliers;
  private Set<SupplierPriceChanged> eligibleSuppliers;
  private PurchaseDecision purchaseDecision;
  private BigDecimalWrapper maxEligiblePrice;
  private Long firstStockThreshold;
  private Long secondStockThreshold;
  private DataStore<SupplierChecker> supplierCheckers;

  public PurchaseUnit() {
    this(100L, new BigDecimal(0.7), DataSource.createSingleton(), DataSource.createSingleton(),
        DataSource.createStore(), new HashSet<SupplierPriceChanged>(), new PurchaseDecision(),
        new BigDecimalWrapper(new BigDecimal(0)), 100L, 200L, DataSource.createStore());
  }

  public PurchaseUnit(Long maxBuy, BigDecimal priceThreshold, SingletonStore<Product> product,
      SingletonStore<Supplier> rise, DataStore<SupplierPriceChanged> suppliers,
      Set<SupplierPriceChanged> eligibleSuppliers, PurchaseDecision purchaseDecision,
      BigDecimalWrapper maxEligiblePrice, Long firstStockThreshold, Long secondStockThreshold,
      DataStore<SupplierChecker> supplierCheckers) {
    this.maxBuy = maxBuy;
    this.priceThreshold = priceThreshold;
    this.product = product;
    this.rise = rise;
    this.suppliers = suppliers;
    this.eligibleSuppliers = eligibleSuppliers;
    this.purchaseDecision = purchaseDecision;
    this.maxEligiblePrice = maxEligiblePrice;
    this.firstStockThreshold = firstStockThreshold;
    this.secondStockThreshold = secondStockThreshold;
  }

  public Set<SupplierPriceChanged> getEligibleSuppliers() {
    return eligibleSuppliers;
  }

  public Long getFirstStockThreshold() {
    return firstStockThreshold;
  }

  public Long getMaxBuy() {
    return maxBuy;
  }

  public BigDecimalWrapper getMaxEligiblePrice() {
    return maxEligiblePrice;
  }

  public BigDecimal getPriceThreshold() {
    return priceThreshold;
  }

  public SingletonStore<Product> getProduct() {
    return product;
  }

  public PurchaseDecision getPurchaseDecision() {
    return purchaseDecision;
  }

  public SingletonStore<Supplier> getRise() {
    return rise;
  }

  public Long getSecondStockThreshold() {
    return secondStockThreshold;
  }

  public DataStore<SupplierChecker> getSupplierCheckers() {
    return supplierCheckers;
  }

  public DataStore<SupplierPriceChanged> getSuppliers() {
    return suppliers;
  }

  public void setEligibleSuppliers(Set<SupplierPriceChanged> eligibleSuppliers) {
    this.eligibleSuppliers = eligibleSuppliers;
  }

  public void setFirstStockThreshold(Long firstStockThreshold) {
    this.firstStockThreshold = firstStockThreshold;
  }

  public void setMaxBuy(Long maxBuy) {
    this.maxBuy = maxBuy;
  }

  public void setMaxEligiblePrice(BigDecimalWrapper maxEligiblePrice) {
    this.maxEligiblePrice = maxEligiblePrice;
  }

  public void setPriceThreshold(BigDecimal priceThreshold) {
    this.priceThreshold = priceThreshold;
  }

  public void setProduct(SingletonStore<Product> product) {
    this.product = product;
  }

  public void setPurchaseDecision(PurchaseDecision purchaseDecision) {
    this.purchaseDecision = purchaseDecision;
  }

  public void setRise(SingletonStore<Supplier> rise) {
    this.rise = rise;
  }

  public void setSecondStockThreshold(Long secondStockThreshold) {
    this.secondStockThreshold = secondStockThreshold;
  }

  public void setSupplierCheckers(DataStore<SupplierChecker> supplierCheckers) {
    this.supplierCheckers = supplierCheckers;
  }

  public void setSuppliers(DataStore<SupplierPriceChanged> suppliers) {
    this.suppliers = suppliers;
  }

}
