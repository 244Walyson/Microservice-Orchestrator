package br.com.microservices.orchestrated.orderservice.core.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    private String source;
    private String status;
    private String message;
    private Instant createdAt;
}
