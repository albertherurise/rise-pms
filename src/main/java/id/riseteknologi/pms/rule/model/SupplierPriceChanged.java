package id.riseteknologi.pms.rule.model;

import java.math.BigDecimal;
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
public class SupplierPriceChanged {

  private UUID id;
  private String name;
  private BigDecimal previousPrice;
  private BigDecimal currentPrice;
  private Long stock;
  private Boolean hasBeenProcessed = false;

}
