package id.riseteknologi.pms.mapper;

import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.dto.WarehouseDTO;
import id.riseteknologi.pms.model.Warehouse;
import id.riseteknologi.pms.rule.input.model.Supplier;

@ApplicationScoped
public class WarehouseMapper {

  public Supplier toSupplierInput(Warehouse warehouse) {
    return new Supplier(warehouse.getSupplier().getId(), warehouse.getSupplier().getName(),
        warehouse.getPrice());
  }

  public Supplier toSupplierInput(WarehouseDTO warehouseDTO) {
    return new Supplier(warehouseDTO.getSupplierId(), warehouseDTO.getSupplierName(),
        warehouseDTO.getPrice());
  }

  public WarehouseDTO toWarehouseDTO(Warehouse warehouse) {
    return new WarehouseDTO(warehouse.getSupplier().getId(), warehouse.getSupplier().getName(),
        warehouse.getProduct().getId(), warehouse.getPrice());
  }
}
