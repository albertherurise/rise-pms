package id.riseteknologi.pms.rule.model;

import java.util.UUID;
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
public class SupplierBuyDecision {

  private UUID supplierId;
  private Long buyAmount;

}
