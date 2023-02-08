package id.riseteknologi.pms.rule.input.model;

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
public class Supplier {

  private UUID id;
  private String name;
  private BigDecimal price;
  private Long stock;

}
