package br.com.microservices.orchestrated.orderservice.core.controller;

import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.dto.EventFilter;
import br.com.microservices.orchestrated.orderservice.core.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService service;

    @GetMapping
    public ResponseEntity<Event> findByFilters(@RequestBody EventFilter filter) {
        return ResponseEntity.ok(service.findByFilter(filter));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
