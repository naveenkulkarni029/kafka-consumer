package org.nbk.demo.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.nbk.demo.kafka.avro.model.Product;

public interface KafkaConsumerService {

    void listen(ConsumerRecord<String, Product> record);
    
    
}

