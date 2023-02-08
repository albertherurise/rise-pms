package id.riseteknologi.pms.model;

import java.util.UUID;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends BaseEntity {

  private String name;

  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  // private List<ClientProductPrice> clientProductPriceList;

  public Client(UUID id, String name) {
    super(id);
    this.name = name;
  }

}
