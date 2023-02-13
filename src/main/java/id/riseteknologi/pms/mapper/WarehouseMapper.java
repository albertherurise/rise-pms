package id.riseteknologi.pms.mapper;

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

  public SupplierPriceChanged toSupplierPriceChangedFromCurrent(WarehouseDTO currentWarehouse) {
    return new SupplierPriceChanged(currentWarehouse.getSupplierId(),
        currentWarehouse.getSupplierName(), currentWarehouse.getPrice(),
        currentWarehouse.getPrice(), currentWarehouse.getStock());
  }

  public SupplierPriceChanged toSupplierPriceChangedFromPrevious(Warehouse previousWarehouse,
      WarehouseDTO currentWarehouse) {
    if (currentWarehouse != null) {
      return new SupplierPriceChanged(currentWarehouse.getSupplierId(),
          currentWarehouse.getSupplierName(), previousWarehouse.getPrice(),
          currentWarehouse.getPrice(), currentWarehouse.getStock());
    }
    return new SupplierPriceChanged(previousWarehouse.getSupplier().getId(),
        previousWarehouse.getSupplier().getName(), previousWarehouse.getPrice(),
        previousWarehouse.getPrice(), previousWarehouse.getStock());
  }

  public WarehouseDTO toWarehouseDTO(Warehouse warehouse) {
    return new WarehouseDTO(warehouse.getSupplier().getId(), warehouse.getSupplier().getName(),
        warehouse.getProduct().getId(), warehouse.getStock(), warehouse.getPrice());
  }
}
