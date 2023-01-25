package id.riseteknologi.pms.rules;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.Transaction;
import id.riseteknologi.pms.rule.model.TransactionResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionUnit implements RuleUnitData {

  private SingletonStore<Transaction> transaction;
  private DataStore<Supplier> suppliers;
  private TransactionResult transactionResult;

  public TransactionUnit() {
    this(DataSource.createSingleton(), DataSource.createStore(), new TransactionResult());
  }

  public TransactionUnit(SingletonStore<Transaction> transaction, DataStore<Supplier> suppliers,
      TransactionResult transactionResult) {
    this.transaction = transaction;
    this.suppliers = suppliers;
    this.transactionResult = transactionResult;
  }

  public void resetTransactionUnit() {
    this.transaction.clear();
    this.suppliers = DataSource.createStore();
    this.transactionResult = new TransactionResult();
  }
}
