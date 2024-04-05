package br.com.microservices.orchestrated.orderservice.core.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private List<OrderProducts> products;
    private Instant createdAt;
    private String transactionId;
    private Double totalAmount;
    private Integer totalItems;
}
