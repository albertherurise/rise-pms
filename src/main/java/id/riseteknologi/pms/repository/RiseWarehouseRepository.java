package id.riseteknologi.pms.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.RiseWarehouse;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class RiseWarehouseRepository implements PanacheRepositoryBase<RiseWarehouse, UUID> {

  public List<RiseWarehouse> getWarehouseByProductId(UUID productId) {
    String query = "FROM RiseWarehouse rw WHERE rw.product.id = :productId";
    return list(query, Parameters.with("productId", productId));
  }
}
