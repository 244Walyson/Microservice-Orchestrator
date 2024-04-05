package br.com.microservices.orchestrated.orderservice.core.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "events")
public class Event {

    @Id
    private String id;
    private String transactionId;
    private String orderId;
    private Order payload;
    private String source;
    private Instant createdAt;
    private String status;
    private List<History> eventHistory;
}
