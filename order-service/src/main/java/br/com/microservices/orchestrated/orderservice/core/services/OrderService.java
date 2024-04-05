package br.com.microservices.orchestrated.orderservice.core.services;

import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.documents.Order;
import br.com.microservices.orchestrated.orderservice.core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.producer.SagaProducer;
import br.com.microservices.orchestrated.orderservice.core.repositories.OrderRepository;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final EventService eventService;
    private final SagaProducer sagaProducer;
    private final JsonUtil jsonUtil;
    private final String TRANSACTION_ID_PATTERN = "%s_%s";

    public Order createOrder(OrderRequest orderRequest) {
        var order = Order
                .builder()
                .products(orderRequest.getProducts())
                .createdAt(Instant.now())
                .transactionId(
                        String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .build();
        orderRepository.save(order);
        var event = createPayload(order);
        sagaProducer.sendEvent(jsonUtil.toJson(event));
        return order;
    }

    private Event createPayload(Order order) {
        var event = Event
                .builder()
                .orderId(order.getId())
                .payload(order)
                .transactionId(order.getTransactionId())
                .createdAt(Instant.now())
                .source("order-service")
                .status("CREATED")
                .build();
        return eventService.save(event);
    }
}
