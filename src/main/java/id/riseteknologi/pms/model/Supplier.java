package id.riseteknologi.pms.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends BaseEntity {

  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
  private List<Warehouse> productWithStockList;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private List<Whitelist> whitelist;

  public Supplier(UUID id, String name) {
    super(id);
    this.name = name;
  }

}
