package id.riseteknologi.pms.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.Warehouse;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class WarehouseRepository implements PanacheRepositoryBase<Warehouse, UUID> {

  public List<Warehouse> getWarehouseBySupplierProductId(UUID supplierId, UUID productId) {
    String query =
        "FROM Warehouse w WHERE w.supplier.id = :supplierId AND w.product.id = :productId";
    return list(query, Parameters.with("supplierId", supplierId).and("productId", productId));
  }
}
