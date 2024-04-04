package br.com.microservices.orchestrated.orchestratorservice.config.kafka;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.BASE_ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.FINISH_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.FINSH_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.INVENTORY_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.INVENTORY_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.PAYMENT_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.PAYMENT_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.PRODUCT_VALIDATION_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopic.START_SAGA;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.RequiredArgsConstructor;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

  private Integer PARTITION_COUNT = 1;
  private Integer REPLICA_COUNT = 1;

  @Value("${spring.kafka.bootstrap-servers}")
  private String boostrapServers;
  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;
  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String autOffsetReset;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerProps());
  }

  private Map<String, Object> consumerProps() {
    Map props = new HashMap<String, Object>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autOffsetReset);
    return props;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerProps());
  }

  private Map<String, Object> producerProps() {
    Map props = new HashMap<String, Object>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  private NewTopic buildTopic(String name) {
    return TopicBuilder
        .name(name)
        .replicas(PARTITION_COUNT)
        .partitions(REPLICA_COUNT)
        .build();
  }

  @Bean
  public NewTopic startSagaTopic() {
    return buildTopic(START_SAGA.getTopic());
  }

  @Bean
  public NewTopic orchestratorTopic() {
    return buildTopic(BASE_ORCHESTRATOR.getTopic());
  }

  @Bean
  public NewTopic finishSuccess() {
    return buildTopic(FINISH_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic finishFail() {
    return buildTopic(FINSH_FAIL.getTopic());
  }

  @Bean
  public NewTopic inventorySuccessTopic() {
    return buildTopic(INVENTORY_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic inventoryFailTopic() {
    return buildTopic(INVENTORY_FAIL.getTopic());
  }

  @Bean
  public NewTopic productValidationSuccessTopic() {
    return buildTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic productValidationFailTopic() {
    return buildTopic(PRODUCT_VALIDATION_FAIL.getTopic());
  }

  @Bean
  public NewTopic paymentSuccessTopic() {
    return buildTopic(PAYMENT_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic paymentFailTopic() {
    return buildTopic(PAYMENT_FAIL.getTopic());
  }

}
