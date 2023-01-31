package id.riseteknologi.pms.rule.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseInput {

  Product product;
  Supplier rise;
  List<SupplierPriceChanged> suppliers;
}
