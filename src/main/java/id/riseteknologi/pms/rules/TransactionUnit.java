package id.riseteknologi.pms.rules;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.rule.input.model.Supplier;
import id.riseteknologi.pms.rule.input.model.SupplierRise;
import id.riseteknologi.pms.rule.input.model.Transaction;
import id.riseteknologi.pms.rule.output.model.TransactionDecision;

public class TransactionUnit implements RuleUnitData {

  private SingletonStore<Transaction> transaction;
  private SingletonStore<SupplierRise> rise;
  private DataStore<Supplier> suppliers;
  private TransactionDecision transactionDecision;

  public TransactionUnit() {
    this(DataSource.createSingleton(), DataSource.createSingleton(), DataSource.createStore(),
        new TransactionDecision());
  }

  public TransactionUnit(SingletonStore<Transaction> transaction, SingletonStore<SupplierRise> rise,
      DataStore<Supplier> suppliers, TransactionDecision transactionDecision) {
    this.transaction = transaction;
    this.rise = rise;
    this.suppliers = suppliers;
    this.transactionDecision = transactionDecision;
  }

  public SingletonStore<SupplierRise> getRise() {
    return rise;
  }

  public DataStore<Supplier> getSuppliers() {
    return suppliers;
  }

  public SingletonStore<Transaction> getTransaction() {
    return transaction;
  }

  public TransactionDecision getTransactionDecision() {
    return transactionDecision;
  }

  public void resetTransactionUnit() {
    this.transaction.clear();
    this.suppliers = DataSource.createStore();
    this.transactionDecision = new TransactionDecision();
  }

  public void setRise(SingletonStore<SupplierRise> rise) {
    this.rise = rise;
  }

  public void setSuppliers(DataStore<Supplier> suppliers) {
    this.suppliers = suppliers;
  }

  public void setTransaction(SingletonStore<Transaction> transaction) {
    this.transaction = transaction;
  }

  public void setTransactionDecision(TransactionDecision transactionDecision) {
    this.transactionDecision = transactionDecision;
  }

}
