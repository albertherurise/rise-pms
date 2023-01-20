package id.riseteknologi.pms.dmn.service;

import id.riseteknologi.pms.dmn.model.BuyDecision;
import it.ssc.pl.milp.LP;
import it.ssc.pl.milp.Solution;
import it.ssc.pl.milp.SolutionType;
import it.ssc.pl.milp.Variable;
import it.ssc.ref.InputString;

public class BuyDecisionService {

  public static void main(String[] args) throws Exception {
    // SolutionClass solution = solveLinearProgramming();
    // System.out.println(solution);
  }

  public static BuyDecision solveBuyDecision(Long a1, Long a2, Long y1, Long y2, Long y3)
      throws Exception {
    String lp_string =
        String.format(" %d   %d    min       .    \n" + "  1    0     le      %d    \n"
            + "  0    1     le      %d    \n" + "  1    1     eq      %d", a1, a2, y1, y2, y3);

    InputString lp_input = new InputString(lp_string);
    lp_input.setInputFormat("X1:double, X2:double, TYPE:varstring(3), RHS:double");

    LP lp = new LP(lp_input);
    SolutionType solution_type = lp.resolve();

    BuyDecision buyDecision = new BuyDecision();

    if (solution_type == SolutionType.OPTIMUM) {
      Solution solution = lp.getSolution();

      for (Variable var : solution.getVariables()) {
        // SscLogger.log("Variable name :" + var.getName() + " value:" +
        // var.getValue());
        if (var.getName().equalsIgnoreCase("X1")) {
          buyDecision.setAlto(Math.round(var.getValue()));
        } else if (var.getName().equalsIgnoreCase("X2")) {
          buyDecision.setSpi(Math.round(var.getValue()));
        }
      }
      // SscLogger.log("o.f. value:" + solution.getOptimumValue());
      buyDecision.setTotalBought(buyDecision.getAlto() + buyDecision.getSpi());
      buyDecision.setTotalNominal(Math.round(solution.getOptimumValue()));
    } else {
      // SscLogger.log("no optimal solution:" + solution_type);
    }
    return buyDecision;
  }
}
