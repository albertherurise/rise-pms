// package id.riseteknologi.pms.rule.endpoint;
//
// import javax.ws.rs.Consumes;
// import javax.ws.rs.POST;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
// import org.drools.ruleunits.api.RuleUnitInstance;
// import org.drools.ruleunits.api.RuleUnitProvider;
// import id.riseteknologi.pms.rule.input.model.SupplierPriceChanged;
// import id.riseteknologi.pms.rule.model.PurchaseInput;
// import id.riseteknologi.pms.rule.output.model.PurchaseDecision;
// import id.riseteknologi.pms.rules.PurchaseUnit;
//
// @Path("purchase")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class PurchaseEndpoint {
//
// private PurchaseUnit purchaseUnit;
// private RuleUnitInstance<PurchaseUnit> instance;
//
// private void createPurchaseUnit() {
// purchaseUnit = new PurchaseUnit();
// instance = RuleUnitProvider.get().createRuleUnitInstance(purchaseUnit);
// }
//
// @POST
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// @Path("/")
// public PurchaseDecision processPurchase(PurchaseInput purchaseInput) {
// createPurchaseUnit();
// System.out.println(purchaseInput);
// purchaseUnit.getProduct().set(purchaseInput.getProduct());
// purchaseUnit.getRise().set(purchaseInput.getRise());
// for (SupplierPriceChanged supplier : purchaseInput.getSuppliers()) {
// purchaseUnit.getSuppliers().add(supplier);
// }
// instance.fire();
// return purchaseUnit.getPurchaseDecision();
// }
//
// }
