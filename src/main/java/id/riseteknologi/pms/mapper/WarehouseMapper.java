package id.riseteknologi.pms.mapper;

import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.dto.WarehouseDTO;
import id.riseteknologi.pms.model.Warehouse;
import id.riseteknologi.pms.rule.input.model.Supplier;
import id.riseteknologi.pms.rule.input.model.SupplierPriceChanged;

@ApplicationScoped
public class WarehouseMapper {

  public Supplier toSupplierInput(Warehouse warehouse) {
    return new Supplier(warehouse.getSupplier().getId(), warehouse.getSupplier().getName(),
        warehouse.getPrice(), warehouse.getStock());
  }

  public Supplier toSupplierInput(WarehouseDTO warehouseDTO) {
    return new Supplier(warehouseDTO.getSupplierId(), warehouseDTO.getSupplierName(),
        warehouseDTO.getPrice(), warehouseDTO.getStock());
  }

  public SupplierPriceChanged toSupplierPriceChanged(Warehouse currentWarehouse,
      WarehouseDTO previousWarehouse) {
    BigDecimal previousPrice = currentWarehouse.getPrice();
    if (previousWarehouse != null) {
      previousPrice = previousWarehouse.getPrice();
    }
    return new SupplierPriceChanged(currentWarehouse.getSupplier().getId(),
        currentWarehouse.getSupplier().getName(), previousPrice, currentWarehouse.getPrice(),
        currentWarehouse.getStock());
  }

  public WarehouseDTO toWarehouseDTO(Warehouse warehouse) {
    return new WarehouseDTO(warehouse.getSupplier().getId(), warehouse.getSupplier().getName(),
        warehouse.getProduct().getId(), warehouse.getStock(), warehouse.getPrice());
  }
}
