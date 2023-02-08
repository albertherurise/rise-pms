package id.riseteknologi.pms.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.Supplier;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class SupplierRepository implements PanacheRepositoryBase<Supplier, UUID> {

  public List<Supplier> getAllExceptRise() {
    String query = "FROM Supplier s WHERE s.name != :riseName";
    return list(query, Parameters.with("riseName", "rise"));
  }

  public Supplier getSupplierByName(String name) {
    return find("name", name).firstResult();
  }
}
