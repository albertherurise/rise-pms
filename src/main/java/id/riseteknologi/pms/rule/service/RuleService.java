package id.riseteknologi.pms.rule.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import id.riseteknologi.pms.rule.model.BuyDecision;
import id.riseteknologi.pms.rule.model.Supplier;
import id.riseteknologi.pms.rule.model.SupplierBuyDecision;

public class RuleService {

  // public static void main(String[] args) {
  // List<Supplier> suppliers = new ArrayList<>();
  // suppliers.add(new Supplier(UUID.fromString("09378fba-ec22-4d37-8eb6-b9a97153fa19"), "alto",
  // new BigDecimal(1000), 100L));
  // suppliers.add(new Supplier(UUID.fromString("a5aba33a-d819-4f3a-9bbf-9ca5c799c5d9"), "spi",
  // new BigDecimal(1025), 500L));
  // System.out.println(solveBuyDecision(suppliers, 400L));
  // }

  public static BuyDecision solveBuyDecision(List<Supplier> suppliers, Long need) {
    Long totalStock = suppliers.stream().mapToLong(s -> s.getStock()).sum();
    Long buy = Math.min(need, totalStock);

    if (suppliers.isEmpty() || buy == 0) {
      return null;
    }

    BuyDecision buyDecision = null;

    int suppliersCount = suppliers.size();
    double[] coefficients = new double[suppliersCount];
    for (int i = 0; i < suppliersCount; i++) {
      coefficients[i] = suppliers.get(i).getPrice().doubleValue();
    }
    LinearObjectiveFunction f = new LinearObjectiveFunction(coefficients, 0);

    Collection<LinearConstraint> constraints = new ArrayList<>();
    double[] constraintCoefficients = new double[suppliersCount];
    for (int i = 0; i < suppliersCount; i++) {
      Arrays.fill(constraintCoefficients, 0);
      constraintCoefficients[i] = 1;
      constraints.add(new LinearConstraint(constraintCoefficients, Relationship.LEQ,
          suppliers.get(i).getStock()));
    }

    Arrays.fill(constraintCoefficients, 1);
    constraints.add(new LinearConstraint(constraintCoefficients, Relationship.EQ, buy));

    for (int i = 0; i < suppliersCount; i++) {
      Arrays.fill(constraintCoefficients, 0);
      constraintCoefficients[i] = 1;
      constraints.add(new LinearConstraint(constraintCoefficients, Relationship.GEQ, 0));
    }

    PointValuePair solution =
        new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MINIMIZE);
    if (solution != null) {
      buyDecision = new BuyDecision();
      for (int i = 0; i < suppliersCount; i++) {
        buyDecision.getSupplierBuyDecisionList().add(
            new SupplierBuyDecision(suppliers.get(i).getId(), Math.round(solution.getPoint()[i])));
      }
      buyDecision.setTotalBuyAmount(buy);
      buyDecision.setTotalBuyNominal(new BigDecimal(Math.round(solution.getValue())));
    }

    return buyDecision;
  }
}
