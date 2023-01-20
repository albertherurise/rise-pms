package id.riseteknologi.pms.dmn.endpoint;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.dmn.rest.DMNJSONUtils;
import id.riseteknologi.pms.dmn.model.BuyDecision;
import id.riseteknologi.pms.dmn.model.DecisionInput;


@Path("purchase-decision-java")
public class DMNInputTest {

  @Inject
  Application application;

  // private DMNRuntime dmnRuntime;
  // private DMNModel dmnModel;

  public void checkNull(Object object) {
    if (object == null) {
      System.out.println(object.getClass() + " NULL");
    }
  }

  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public BuyDecision getBuyDecisionResult(DecisionInput decisionInput) {
    String namespace = "https://kiegroup.org/dmn/_E524CD31-614A-4A74-8310-5159341AB4CD";
    String modelName = "purchase-decision";
    DecisionModel decisionModel =
        application.get(DecisionModels.class).getDecisionModel(namespace, modelName);
    checkNull(decisionModel);

    Map<String, Object> context = new HashMap<>();
    context.put("alto", decisionInput.getAlto());
    context.put("spi", decisionInput.getSpi());
    context.put("need", decisionInput.getNeed());
    // dmnContext.set("alto", decisionInput.getAlto());
    // dmnContext.set("spi", decisionInput.getSpi());
    // dmnContext.set("need", decisionInput.getNeed());
    DMNResult dmnResult = decisionModel.evaluateAll(DMNJSONUtils.ctx(decisionModel, context));
    BuyDecision buyDecision = null;
    for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
      if (dr.getDecisionName().equals("optimal solution")) {
        System.out.println(dr.getDecisionName() + " = " + dr.getResult());
        System.out.println(dr.getResult().getClass());
        buyDecision = (BuyDecision) dr.getResult();
      }
      // System.out.println(dr.getDecisionName() + " = " + dr.getResult());
    }
    System.out.println("BuyDecision: " + buyDecision);
    return buyDecision;
  }


  // public void init() {
  // KieServices kieServices = KieServices.Factory.get();
  // KieContainer kieContainer =
  // KieServices.Factory.get().getKieClasspathContainer(this.getClass().getClassLoader());
  // // dmnRuntime = KieRuntimeFactory.of(kieContainer.getKieBase("dmn")).get(DMNRuntime.class);
  // //
  // // String namespace = "https://kiegroup.org/dmn/_E524CD31-614A-4A74-8310-5159341AB4CD";
  // // String modelName = "purchase-decision";
  // // dmnModel = dmnRuntime.getModel(namespace, modelName);
  // }

}
