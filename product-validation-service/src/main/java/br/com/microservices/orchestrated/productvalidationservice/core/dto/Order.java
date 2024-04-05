package br.com.microservices.orchestrated.productvalidationservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private List<OrderProduct> products;
    private Instant createdAt;
    private String transactionId;
    private Double totalAmount;
    private Integer totalItems;
}
