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
public class Supplier {

  private UUID id;
  private String name;
  private BigDecimal price;
  private Long stock;
  private Boolean hasBeenProcessed = false;

  public Supplier(UUID id, String name, BigDecimal price, Long stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

}
