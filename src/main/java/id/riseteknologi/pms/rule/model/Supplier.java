package id.riseteknologi.pms.rule.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

  private UUID id;
  private String name;
  private String price;
  private String availability;

}
