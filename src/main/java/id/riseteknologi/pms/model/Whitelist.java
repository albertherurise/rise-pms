package id.riseteknologi.pms.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@ToString(callSuper = true)
@Table(name = "whitelist", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "product_id", "supplier_id"})})
public class Whitelist extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  public Whitelist(UUID id, Client client, Product product, Supplier supplier) {
    super(id);
    this.client = client;
    this.product = product;
    this.supplier = supplier;
  }


}
