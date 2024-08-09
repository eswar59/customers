package com.piggy.customers.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import shared.CustomerOrder;

@Service
public class ProducerService {

    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

    private KafkaTemplate<String, CustomerOrder> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, CustomerOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CustomerOrder customerOrder){

        log.info(String.format("The message sent is %s", customerOrder.toString()));

        Message<CustomerOrder> message = MessageBuilder
                .withPayload(customerOrder)
                .setHeader(KafkaHeaders.TOPIC, "foodOrder")
                .build();

        kafkaTemplate.send(message);
    }
}