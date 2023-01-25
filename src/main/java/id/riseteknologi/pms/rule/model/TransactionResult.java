package id.riseteknologi.pms.rule.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResult {

  private UUID productId;
  private Boolean possible;
  private Boolean buy;
  private UUID supplierId;

}
