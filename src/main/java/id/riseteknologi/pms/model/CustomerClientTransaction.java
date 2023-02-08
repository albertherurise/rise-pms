// package id.riseteknologi.pms.model;
//
// import java.time.LocalDateTime;
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
// public class CustomerClientTransaction extends BaseTransaction {
//
// @ManyToOne
// @JoinColumn(name = "client_id")
// private Client client;
//
// @ManyToOne
// @JoinColumn(name = "customer_id")
// private Customer customer;
//
// @ManyToOne
// @JoinColumn(name = "product_id")
// private Product product;
//
// private String customerPhone;
//
// public CustomerClientTransaction(UUID id, LocalDateTime createdTimestamp,
// LocalDateTime successfulTimestamp, String status, Client client, Customer customer,
// Product product, String customerPhone) {
// super(id, createdTimestamp, successfulTimestamp, status);
// this.client = client;
// this.customer = customer;
// this.product = product;
// this.customerPhone = customerPhone;
// }
//
// }
