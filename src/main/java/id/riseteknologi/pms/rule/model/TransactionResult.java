package id.riseteknologi.pms.rule.model;

import java.math.BigDecimal;
import id.riseteknologi.pms.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResult {

  private Product product;
  private String status;
  private String willBuy;
  private Supplier buyFrom;
  private BigDecimal price;


}
