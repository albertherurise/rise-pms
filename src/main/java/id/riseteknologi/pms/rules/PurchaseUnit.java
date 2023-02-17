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
import id.riseteknologi.pms.rule.input.model.SupplierRise;
import id.riseteknologi.pms.rule.model.BigDecimalWrapper;
import id.riseteknologi.pms.rule.output.model.PurchaseDecision;

public class PurchaseUnit implements RuleUnitData {

  public static Boolean isLessThan(BigDecimal currentPrice, BigDecimalWrapper maxPrice) {
    return currentPrice.compareTo(maxPrice.getValue()) <= 0;
  }

  public static Boolean isLessThanPriceThreshold(BigDecimal currentPrice, BigDecimal risePrice,
      BigDecimal priceThreshold) {
    return currentPrice.compareTo(priceThreshold.multiply(risePrice)) <= 0;
  }

  private Long maxBuy;
  private BigDecimal priceThreshold;
  private SingletonStore<Product> product;
  private SingletonStore<SupplierRise> rise;
  private DataStore<Supplier> previousSuppliers;
  private DataStore<Supplier> currentSuppliers;
  private Set<Supplier> eligibleSuppliers;
  private PurchaseDecision purchaseDecision;
  private BigDecimalWrapper maxEligiblePrice;
  private Long firstStockThreshold;
  private Long secondStockThreshold;
  private DataStore<SupplierChecker> supplierCheckers;

  public PurchaseUnit() {
    this(100L, new BigDecimal(0.7), DataSource.createSingleton(), DataSource.createSingleton(),
        DataSource.createStore(), DataSource.createStore(), new HashSet<Supplier>(),
        new PurchaseDecision(), new BigDecimalWrapper(new BigDecimal(0)), 100L, 200L,
        DataSource.createStore());
  }

  public PurchaseUnit(Long maxBuy, BigDecimal priceThreshold, SingletonStore<Product> product,
      SingletonStore<SupplierRise> rise, DataStore<Supplier> previousSuppliers,
      DataStore<Supplier> currentSuppliers, Set<Supplier> eligibleSuppliers,
      PurchaseDecision purchaseDecision, BigDecimalWrapper maxEligiblePrice,
      Long firstStockThreshold, Long secondStockThreshold,
      DataStore<SupplierChecker> supplierCheckers) {
    this.maxBuy = maxBuy;
    this.priceThreshold = priceThreshold;
    this.product = product;
    this.rise = rise;
    this.previousSuppliers = previousSuppliers;
    this.currentSuppliers = currentSuppliers;
    this.eligibleSuppliers = eligibleSuppliers;
    this.purchaseDecision = purchaseDecision;
    this.maxEligiblePrice = maxEligiblePrice;
    this.firstStockThreshold = firstStockThreshold;
    this.secondStockThreshold = secondStockThreshold;
    this.supplierCheckers = supplierCheckers;
  }

  public DataStore<Supplier> getCurrentSuppliers() {
    return currentSuppliers;
  }

  public Set<Supplier> getEligibleSuppliers() {
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

  public DataStore<Supplier> getPreviousSuppliers() {
    return previousSuppliers;
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

  public SingletonStore<SupplierRise> getRise() {
    return rise;
  }

  public Long getSecondStockThreshold() {
    return secondStockThreshold;
  }

  public DataStore<SupplierChecker> getSupplierCheckers() {
    return supplierCheckers;
  }

  public void setCurrentSuppliers(DataStore<Supplier> currentSuppliers) {
    this.currentSuppliers = currentSuppliers;
  }

  public void setEligibleSuppliers(Set<Supplier> eligibleSuppliers) {
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

  public void setPreviousSuppliers(DataStore<Supplier> previousSuppliers) {
    this.previousSuppliers = previousSuppliers;
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

  public void setRise(SingletonStore<SupplierRise> rise) {
    this.rise = rise;
  }

  public void setSecondStockThreshold(Long secondStockThreshold) {
    this.secondStockThreshold = secondStockThreshold;
  }

  public void setSupplierCheckers(DataStore<SupplierChecker> supplierCheckers) {
    this.supplierCheckers = supplierCheckers;
  }
}
