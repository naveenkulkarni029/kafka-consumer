package org.nbk.demo.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.nbk.demo.kafka.consumer.service.KafkaConsumerService;
import org.nbk.demo.kafka.consumer.service.KafkaConsumerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumerConfguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
	Map<String, Object> configProperties = new HashMap<>();
	configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
	return new DefaultKafkaConsumerFactory<>(configProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
    }
    
    @Bean
    public KafkaConsumerService kafkaConsumerService() {
	return new KafkaConsumerServiceImpl();
    }
}
