package id.riseteknologi.pms.dmn.service;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import id.riseteknologi.pms.dmn.model.BuyDecision;

public class BuyDecisionService {

  public static void main(String[] args) {
    solveBuyDecision(1000L, 1025L, 100L, 500L, 400L);
  }

  public static BuyDecision solveBuyDecision(Long a1, Long a2, Long y1, Long y2, Long y3) {
    LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {a1, a2}, 0);

    Collection<LinearConstraint> constraints = new ArrayList<>();
    constraints.add(new LinearConstraint(new double[] {1, 0}, Relationship.LEQ, y1));
    constraints.add(new LinearConstraint(new double[] {0, 1}, Relationship.LEQ, y2));
    constraints.add(new LinearConstraint(new double[] {1, 1}, Relationship.EQ, y3));

    constraints.add(new LinearConstraint(new double[] {1, 0}, Relationship.GEQ, 0));
    constraints.add(new LinearConstraint(new double[] {0, 1}, Relationship.GEQ, 0));

    PointValuePair solution =
        new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MINIMIZE);
    BuyDecision buyDecision = null;
    if (solution != null) {
      buyDecision = new BuyDecision();
      for (int i = 0; i < 2; i++) {
        if (i == 0) {
          buyDecision.setAlto(Math.round(solution.getPoint()[i]));
        } else {
          buyDecision.setSpi(Math.round(solution.getPoint()[i]));
        }
      }
      buyDecision.setTotalBought(buyDecision.getAlto() + buyDecision.getSpi());
      buyDecision.setTotalNominal(Math.round(solution.getValue()));
    }
    System.out.println(buyDecision);
    return buyDecision;
  }
}
