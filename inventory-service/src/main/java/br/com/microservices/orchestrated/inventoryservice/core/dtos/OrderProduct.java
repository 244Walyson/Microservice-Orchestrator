package br.com.microservices.orchestrated.inventoryservice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProduct {

    private Product product;
    private Integer qunatity;
}
