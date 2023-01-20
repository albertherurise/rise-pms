package id.riseteknologi.pms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRiseTransaction extends BaseTransaction {

  @ManyToOne
  @JoinColumn(name = "customer_client_transaction_id")
  private CustomerClientTransaction customerClientTransaction;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  private BigDecimal price;

  public ClientRiseTransaction(UUID id, LocalDateTime createdTimestamp,
      LocalDateTime successfulTimestamp, String status,
      CustomerClientTransaction customerClientTransaction, Supplier supplier) {
    super(id, createdTimestamp, successfulTimestamp, status);
    this.customerClientTransaction = customerClientTransaction;
    this.supplier = supplier;
  }

}
