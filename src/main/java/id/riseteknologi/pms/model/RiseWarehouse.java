package id.riseteknologi.pms.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rise_warehouse")
public class RiseWarehouse extends Warehouse {

  private Long stock;

  public RiseWarehouse(UUID id, Supplier supplier, Product product, BigDecimal price, Long stock) {
    super(id, supplier, product, price);
    this.stock = stock;
  }

}
