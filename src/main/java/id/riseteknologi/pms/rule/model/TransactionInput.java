package id.riseteknologi.pms.rule.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {

  Transaction transaction;
  Supplier rise;
  Supplier alto;
  Supplier spi;

}
