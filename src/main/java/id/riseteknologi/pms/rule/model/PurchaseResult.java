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
public class PurchaseResult {

  private UUID productId;
  private Boolean buy;
  private Boolean alto;
  private Boolean spi;
  private Long altoCount;
  private Long spiCount;
  private Long totalBuyCount;

}
