package br.com.microservices.orchestrated.paymentservice.core.dtos;

import br.com.microservices.orchestrated.paymentservice.core.enums.ESagaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    private String source;
    private ESagaStatus status;
    private String message;
    private Instant createdAt;

}
