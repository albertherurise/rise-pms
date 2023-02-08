// package id.riseteknologi.pms.rule.endpoint;
//
// import javax.ws.rs.Consumes;
// import javax.ws.rs.POST;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
// import org.drools.ruleunits.api.RuleUnitInstance;
// import org.drools.ruleunits.api.RuleUnitProvider;
// import id.riseteknologi.pms.rule.input.model.Supplier;
// import id.riseteknologi.pms.rule.input.model.SupplierRise;
// import id.riseteknologi.pms.rule.model.TransactionInput;
// import id.riseteknologi.pms.rule.output.model.TransactionDecision;
// import id.riseteknologi.pms.rules.TransactionUnit;
//
// @Path("transaction")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class TransactionEndpoint {
//
// private TransactionUnit transactionUnit;
// private RuleUnitInstance<TransactionUnit> instance;
//
// private void createTransactionUnit() {
// transactionUnit = new TransactionUnit();
// instance = RuleUnitProvider.get().createRuleUnitInstance(transactionUnit);
// }
//
// @POST
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// @Path("/")
// public TransactionDecision processTransaction(TransactionInput transactionInput) {
// createTransactionUnit();
// transactionUnit.getTransaction().set(transactionInput.getTransaction());
// transactionUnit.getRise().set(new SupplierRise(transactionInput.getRise().getStock()));
// for (Supplier supplier : transactionInput.getSuppliers()) {
// transactionUnit.getSuppliers().add(supplier);
// }
// instance.fire();
// return transactionUnit.getTransactionDecision();
// }
// }
