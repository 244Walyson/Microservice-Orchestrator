package br.com.microservices.orchestrated.orderservice.core.controller;

import br.com.microservices.orchestrated.orderservice.core.documents.Order;
import br.com.microservices.orchestrated.orderservice.core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(service.createOrder(order));
    }
}
