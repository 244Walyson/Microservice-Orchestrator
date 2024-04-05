package br.com.microservices.orchestrated.orderservice.core.services;

import br.com.microservices.orchestrated.orderservice.config.exceptions.ValidationException;
import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.dto.EventFilter;
import br.com.microservices.orchestrated.orderservice.core.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@AllArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void notifyEvent(Event event) {
      event.setCreatedAt(Instant.now());
      save(event);
        log.info("Event {} notified", event.toString());
    }

    public List<Event> findAll() {
        return eventRepository.findAllByOrderByCreatedAtDesc();
    }

    public Event findByFilter(EventFilter filter) {
        validateFilter(filter);
        if(!isEmpty(filter.getOrderId())) {
            return findByOrderId(filter.getOrderId());
        }
        return findByTransactionId(filter.getTransactionId());
    }

    private Event findByOrderId(String orderId) {
        return eventRepository
                .findTop1ByOrderIdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> {
                    throw new ValidationException("Event not found by transaction id.");
                });
    }
    private Event findByTransactionId(String transactionId) {
        return eventRepository
                .findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
                .orElseThrow(() -> {
                    throw new ValidationException("Event not found by transaction id.");
                });
    }

    private void validateFilter(EventFilter filter) {
        if (isEmpty(filter.getOrderId()) && isEmpty(filter.getTransactionId())) {
            throw new ValidationException("Order id or transaction id must be informed.");
        }
    }
}
