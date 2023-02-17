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
public class SupplierRise extends Supplier {

  private Long stock;

  public SupplierRise(UUID id, String name, BigDecimal price, Long stock) {
    super(id, name, price);
    this.stock = stock;
  }

}
