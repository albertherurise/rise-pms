package id.riseteknologi.pms.event;

import java.util.List;
import java.util.UUID;
import id.riseteknologi.pms.dto.WarehouseDTO;
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
public class PurchaseEvent {
  private UUID productId;
  List<WarehouseDTO> previousWarehouses;
}
