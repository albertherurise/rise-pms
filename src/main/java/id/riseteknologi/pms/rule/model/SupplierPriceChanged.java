package id.riseteknologi.pms.rule.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPriceChanged {

  private UUID id;
  private String name;
  private BigDecimal previousPrice;
  private BigDecimal currentPrice;
  private Long stock;

}
