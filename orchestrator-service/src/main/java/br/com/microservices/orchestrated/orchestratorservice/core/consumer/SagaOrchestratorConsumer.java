package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SagaOrchestratorConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStartSagaEvent(String payload) {
        log.info("Consuming start saga {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event notify-ending consumed {}", event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.orchestrator}"
    )
    public void consumeOrchestratorEvent(String payload) {
        log.info("Consuming orchestrator {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event notify-ending consumed {}", event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-success}"
    )
    public void consumeFinishSuccess(String payload) {
        log.info("Consuming finish success {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event notify-ending consumed {}", event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-fail}"
    )
    public void consumeFinishFail(String payload) {
        log.info("Consuming finish fail {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event notify-ending consumed {}", event);
    }
}
