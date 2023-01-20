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
public class Client extends BaseEntity {

  private String aliasName;
  private String name;
  private String partyName;
  private String refNumberLength;
  private UUID corporateId;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private List<ClientProductPrice> clientProductPriceList;

  public Client(UUID id, String aliasName, String name, String partyName, String refNumberLength,
      UUID corporateId) {
    super(id);
    this.aliasName = aliasName;
    this.name = name;
    this.partyName = partyName;
    this.refNumberLength = refNumberLength;
    this.corporateId = corporateId;
  }

}
