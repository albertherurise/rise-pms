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
public class Customer extends BaseEntity {

  private String name;
  private String phone;
  private String domicile;

  public Customer(UUID id, String name, String phone, String domicile) {
    super(id);
    this.name = name;
    this.phone = phone;
    this.domicile = domicile;
  }

}
