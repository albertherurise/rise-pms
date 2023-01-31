package id.riseteknologi.pms.rule.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
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

}
