package id.riseteknologi.pms.rule.output.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import id.riseteknologi.pms.rule.input.model.Supplier;
import id.riseteknologi.pms.rule.input.model.SupplierPriceChanged;
import id.riseteknologi.pms.rule.service.RuleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDecision {

  private UUID productId;
  private Boolean buy;
  private BuyDecision buyDecision;

  public void processEligibleSuppliers(Set<SupplierPriceChanged> eligibleSuppliers, Long maxBuy) {
    List<Supplier> suppliers = eligibleSuppliers.stream()
        .map(s -> new Supplier(s.getId(), s.getName(), s.getCurrentPrice(), s.getStock()))
        .collect(Collectors.toList());
    BuyDecision buyDecision = RuleService.solveBuyDecision(suppliers, maxBuy);
    if (buyDecision == null) {
      setBuy(false);
    } else {
      setBuy(true);
      setBuyDecision(buyDecision);
    }
  }

  public void processEligibleSuppliersV2(Set<Supplier> eligibleSuppliers, Long maxBuy) {
    List<Supplier> suppliers = new ArrayList<>(eligibleSuppliers);
    BuyDecision buyDecision = RuleService.solveBuyDecision(suppliers, maxBuy);
    if (buyDecision == null) {
      setBuy(false);
    } else {
      setBuy(true);
      setBuyDecision(buyDecision);
    }
  }
}
