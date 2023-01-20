package id.riseteknologi.pms.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product extends BaseEntity {

  private String code;
  private String name;
  private String description;

  public Product(UUID id, String code, String name, String description) {
    super(id);
    this.code = code;
    this.name = name;
    this.description = description;
  }

}
