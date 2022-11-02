package com.example.cyberycon.smartmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;

import groovy.util.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class TestListener {

    private CountDownLatch latch ;

    public TestListener() {
        Logger logger = LoggerFactory.getLogger(TestListener.class);
        logger.info("Constructing test listener");
    }

    @RabbitListener(queues = "aggregator")
    public void listen(String messageContent) {
        String[] values = messageContent.split(":");
        assertEquals(3, values.length);
        if (latch != null) {
            latch.countDown();
        }
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
