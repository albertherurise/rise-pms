package id.riseteknologi.pms.rule.input.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierChecker {

  private UUID id;
  private Boolean processed = false;

  public SupplierChecker(UUID id) {
    this.id = id;
  }

}
