package id.riseteknologi.pms.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@ToString(callSuper = true)
@Table(name = "blacklist_reason")
public class BlacklistReason extends BaseEntity {

  private String description;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "blacklistReason")
  List<Blacklist> blacklists;

  public BlacklistReason(UUID id, String description) {
    super(id);
    this.description = description;
  }

}
