package id.riseteknologi.pms.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

  private UUID supplierId;
  private String supplierName;
  private UUID productId;
  private Long stock;
  private BigDecimal price;

}
