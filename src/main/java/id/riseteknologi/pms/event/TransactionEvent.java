package id.riseteknologi.pms.event;

import java.util.UUID;
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
public class TransactionEvent {

  private UUID clientId;
  private UUID customerId;
  private UUID productId;
  // private LocalDateTime transactionTime;
}
