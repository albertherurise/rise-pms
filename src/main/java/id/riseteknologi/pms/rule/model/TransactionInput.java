package id.riseteknologi.pms.rule.model;

import java.util.List;
import id.riseteknologi.pms.rule.input.model.Supplier;
import id.riseteknologi.pms.rule.input.model.Transaction;
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
public class TransactionInput {

  Transaction transaction;
  Supplier rise;
  List<Supplier> suppliers;

}
