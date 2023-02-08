package id.riseteknologi.pms.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Warehouse extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private Long stock;

  private BigDecimal price;

  public Warehouse(UUID id, Supplier supplier, Product product, Long stock, BigDecimal price) {
    super(id);
    this.supplier = supplier;
    this.product = product;
    this.stock = stock;
    this.price = price;
  }

}
