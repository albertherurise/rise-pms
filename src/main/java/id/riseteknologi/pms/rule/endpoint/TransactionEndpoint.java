package id.riseteknologi.pms.rule.endpoint;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import id.riseteknologi.pms.rule.model.TransactionInput;
import id.riseteknologi.pms.rule.model.TransactionResult;
import id.riseteknologi.pms.rules.TransactionUnit;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionEndpoint {

  private TransactionUnit transactionUnit;
  private RuleUnitInstance<TransactionUnit> instance;

  @PostConstruct
  private void createTransactionUnit() {
    transactionUnit = new TransactionUnit();
    instance = RuleUnitProvider.get().createRuleUnitInstance(transactionUnit);
  }

  @PreDestroy
  private void destroyRuleUnitInstance() {
    instance.close();
  }

  @POST
  @Path("/")
  public TransactionResult processTransaction(TransactionInput transactionInput) {
    transactionUnit.resetTransactionUnit();
    transactionUnit.getTransaction().set(transactionInput.getTransaction());
    transactionUnit.getSuppliers().add(transactionInput.getRise());
    transactionUnit.getSuppliers().add(transactionInput.getAlto());
    transactionUnit.getSuppliers().add(transactionInput.getSpi());
    instance.fire();
    return transactionUnit.getTransactionResult();
  }
}
