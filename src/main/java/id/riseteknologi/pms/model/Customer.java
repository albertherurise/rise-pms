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
public class Customer extends BaseEntity {

  private String name;
  private String phone;
  private String domicile;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
  private List<Blacklist> blacklists;

  public Customer(UUID id, String name, String phone, String domicile) {
    super(id);
    this.name = name;
    this.phone = phone;
    this.domicile = domicile;
  }

}
