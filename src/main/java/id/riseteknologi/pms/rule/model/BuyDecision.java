package id.riseteknologi.pms.rule.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyDecision {

  private List<SupplierBuyDecision> supplierBuyDecisionList = new ArrayList<>();
  private Long totalBuyAmount;
  private BigDecimal totalBuyNominal;

}
