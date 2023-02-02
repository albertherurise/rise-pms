package id.riseteknologi.pms.rule.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.TransactionInput;
import id.riseteknologi.pms.rule.model.TransactionResult;
import id.riseteknologi.pms.rules.TransactionUnit;
import io.quarkus.logging.Log;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionEndpoint {

  private TransactionUnit transactionUnit;
  private RuleUnitInstance<TransactionUnit> instance;

  private void createTransactionUnit() {
    transactionUnit = new TransactionUnit();
    if (transactionUnit == null) {
      Log.info("TRANSACTION UNIT NULL");
    } else {
      Log.info("TRANSACTION UNIT NOT NULL");
    }
    try {
      instance = RuleUnitProvider.get().createRuleUnitInstance(transactionUnit);
    } catch (Exception e) {
      Log.info("THERE'S SEEMS TO BE A PROBLEM WHEN INITIALIZING INSTANCE");
    }
    if (instance == null) {
      Log.info("INSTANCE NULL");
    } else {
      Log.info("INSTANCE NOT NULL");
    }
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/")
  public TransactionResult processTransaction(TransactionInput transactionInput) {
    Log.info(transactionInput);
    createTransactionUnit();
    transactionUnit.getTransaction().set(transactionInput.getTransaction());
    transactionUnit.getRise().set(transactionInput.getRise());
    for (Supplier supplier : transactionInput.getSuppliers()) {
      transactionUnit.getSuppliers().add(supplier);
    }
    instance.fire();
    return transactionUnit.getTransactionResult();
  }
}
