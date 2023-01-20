package id.riseteknologi.pms.repository;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import id.riseteknologi.pms.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {

}
