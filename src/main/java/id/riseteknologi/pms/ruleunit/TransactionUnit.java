package id.riseteknologi.pms.ruleunit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.Transaction;
import id.riseteknologi.pms.rule.model.TransactionResult;
import lombok.Getter;

@Getter
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
}
