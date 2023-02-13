package id.riseteknologi.pms.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.Whitelist;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class WhitelistRepository implements PanacheRepositoryBase<Whitelist, UUID> {

  public List<Whitelist> getWhitelistByClientProduct(UUID clientId, UUID productId) {
    String query = "FROM Whitelist w WHERE w.client.id = :clientId AND w.product.id = :productId";
    return list(query, Parameters.with("clientId", clientId).and("productId", productId));
  }
}
