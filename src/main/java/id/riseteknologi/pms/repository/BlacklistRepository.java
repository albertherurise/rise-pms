package id.riseteknologi.pms.repository;

import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.Blacklist;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class BlacklistRepository implements PanacheRepositoryBase<Blacklist, UUID> {

  public Boolean isBlacklisted(UUID customerId) {
    String query = "FROM Blacklist b WHERE b.customer.id = :customerId";
    List<Blacklist> blacklists = list(query, Parameters.with("customerId", customerId));
    if (blacklists == null || blacklists.isEmpty()) {
      return false;
    }
    return true;
  }
}
