package id.riseteknologi.pms.dmn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecisionInput {

  private Supplier alto;
  private Supplier spi;
  private Long need;

}
