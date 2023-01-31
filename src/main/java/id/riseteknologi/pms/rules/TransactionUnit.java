package id.riseteknologi.pms.rules;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.Transaction;
import id.riseteknologi.pms.rule.model.TransactionResult;

public class TransactionUnit implements RuleUnitData {

  private SingletonStore<Transaction> transaction;
  private SingletonStore<Supplier> rise;
  private DataStore<Supplier> suppliers;
  private TransactionResult transactionResult;

  public TransactionUnit() {
    this(DataSource.createSingleton(), DataSource.createSingleton(), DataSource.createStore(),
        new TransactionResult());
  }

  public TransactionUnit(SingletonStore<Transaction> transaction, SingletonStore<Supplier> rise,
      DataStore<Supplier> suppliers, TransactionResult transactionResult) {
    this.transaction = transaction;
    this.rise = rise;
    this.suppliers = suppliers;
    this.transactionResult = transactionResult;
  }

  public SingletonStore<Supplier> getRise() {
    return rise;
  }

  public DataStore<Supplier> getSuppliers() {
    return suppliers;
  }

  public SingletonStore<Transaction> getTransaction() {
    return transaction;
  }

  public TransactionResult getTransactionResult() {
    return transactionResult;
  }

  public void resetTransactionUnit() {
    this.transaction.clear();
    this.suppliers = DataSource.createStore();
    this.transactionResult = new TransactionResult();
  }

  public void setRise(SingletonStore<Supplier> rise) {
    this.rise = rise;
  }

  public void setSuppliers(DataStore<Supplier> suppliers) {
    this.suppliers = suppliers;
  }

  public void setTransaction(SingletonStore<Transaction> transaction) {
    this.transaction = transaction;
  }

  public void setTransactionResult(TransactionResult transactionResult) {
    this.transactionResult = transactionResult;
  }


}
