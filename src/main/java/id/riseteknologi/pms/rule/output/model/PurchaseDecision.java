package id.riseteknologi.pms.rule.output.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import id.riseteknologi.pms.rule.input.model.Supplier;
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
  private List<Supplier> eligibleSuppliers;

  public void processEligibleSuppliers(Set<Supplier> eligibleSuppliers, Long maxBuy) {
    List<Supplier> suppliers = new ArrayList<>(eligibleSuppliers);
    Collections.sort(suppliers, (s1, s2) -> s1.getPrice().compareTo(s2.getPrice()));
    if (suppliers.isEmpty()) {
      setBuy(false);
    } else {
      setBuy(true);
      setEligibleSuppliers(suppliers);
    }
  }
}
