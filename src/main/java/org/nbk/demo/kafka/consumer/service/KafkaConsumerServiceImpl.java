package org.nbk.demo.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = "testMessage", groupId="group-id")
    public void listen(String message) {
	System.out.println("Message: is equal to the producer message" + message);
    }

}
