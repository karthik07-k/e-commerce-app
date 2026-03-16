package com.org.ecom.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        logger.info("Order confirmation sent: {}", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
        .build();
        kafkaTemplate.send(message);
    }
}
