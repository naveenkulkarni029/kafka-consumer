package org.nbk.demo.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.nbk.demo.kafka.avro.model.Product;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = "my-avro-topic", groupId="group-id1")
    public void listen(ConsumerRecord<String, Product> record) {
	System.out.println("Message: is equal to the producer message" + record.value());
    }

}
