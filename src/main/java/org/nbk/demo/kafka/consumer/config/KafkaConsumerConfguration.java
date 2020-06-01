package org.nbk.demo.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.nbk.demo.kafka.avro.model.Product;
import org.nbk.demo.kafka.consumer.service.KafkaConsumerService;
import org.nbk.demo.kafka.consumer.service.KafkaConsumerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfguration {

    // De-serializer class should be specified for both key and value and it should
    // correspond to the type specification of the producer message that was
    // serialized with the same serialization class.

    // KafkaConsumer consumer = new KafkaConsumer(propreties);
    // consumer.subscribe(<collection of topics>);
    // consumer.unsubscribe() --> unsubscribes the topic
    // alternatively, consumer.subscribe(<empty List>)

    @Bean
    public ConsumerFactory<String, Product> consumerFactory() {
	Map<String, Object> configProperties = new HashMap<>();
	configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
	configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
	configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id1");
	configProperties.put("specific.avro.reader", "true");
	configProperties.put("schema.registry.url", "http://localhost:8089");
	return new DefaultKafkaConsumerFactory<>(configProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, Product> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
    }

    @Bean
    public KafkaConsumerService kafkaConsumerService() {
	return new KafkaConsumerServiceImpl();
    }
}
