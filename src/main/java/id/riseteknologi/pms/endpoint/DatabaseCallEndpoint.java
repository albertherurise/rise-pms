package id.riseteknologi.pms.endpoint;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import id.riseteknologi.pms.dto.WarehouseDTO;
import id.riseteknologi.pms.mapper.WarehouseMapper;
import id.riseteknologi.pms.repository.WarehouseRepository;

@Path("db-call")
public class DatabaseCallEndpoint {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  WarehouseMapper warehouseMapper;

  @GET
  @Path("/warehouse")
  @Produces(MediaType.APPLICATION_JSON)
  public List<WarehouseDTO> getWarehouse() {
    UUID supplierId = UUID.fromString("de848139-a8d6-4a8d-b3be-192a48b31528");
    UUID productId = UUID.fromString("84d80f61-3a1e-4035-b1e8-e02c5164c3f9");
    return warehouseRepository.getWarehouseBySupplierProductId(supplierId, productId).stream()
        .map(w -> warehouseMapper.toWarehouseDTO(w)).collect(Collectors.toList());
  }
}
