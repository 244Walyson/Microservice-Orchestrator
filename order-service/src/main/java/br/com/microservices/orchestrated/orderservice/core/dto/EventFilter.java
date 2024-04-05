package br.com.microservices.orchestrated.orderservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventFilter {

    private String orderId;
    private String transactionId;
}
