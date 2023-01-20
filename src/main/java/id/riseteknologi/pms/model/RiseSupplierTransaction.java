package id.riseteknologi.pms.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiseSupplierTransaction extends BaseTransaction {

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private Long quantity;

  public RiseSupplierTransaction(UUID id, LocalDateTime createdTimestamp,
      LocalDateTime successfulTimestamp, String status, Supplier supplier, Product product,
      Long quantity) {
    super(id, createdTimestamp, successfulTimestamp, status);
    this.supplier = supplier;
    this.product = product;
    this.quantity = quantity;
  }

}
