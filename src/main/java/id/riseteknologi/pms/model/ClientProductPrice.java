// package id.riseteknologi.pms.model;
//
// import java.math.BigDecimal;
// import java.util.UUID;
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
//
// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class ClientProductPrice extends BaseEntity {
//
// @ManyToOne
// @JoinColumn(name = "client_id")
// private Client client;
//
// @ManyToOne
// @JoinColumn(name = "product_id")
// private Product product;
//
// private BigDecimal price;
//
// public ClientProductPrice(UUID id, Client client, Product product, BigDecimal price) {
// super(id);
// this.client = client;
// this.product = product;
// this.price = price;
// }
//
// }
