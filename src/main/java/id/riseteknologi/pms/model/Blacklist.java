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
@Table(
    uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "blacklist_reason_id"})})
public class Blacklist extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "blacklist_reason_id")
  private BlacklistReason blacklistReason;

  public Blacklist(UUID id, Customer customer, BlacklistReason blacklistReason) {
    super(id);
    this.customer = customer;
    this.blacklistReason = blacklistReason;
  }

}
