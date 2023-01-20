package id.riseteknologi.pms.dmn.model;

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

  private Long alto;
  private Long spi;
  private Long totalBought;
  private Long totalNominal;

}
