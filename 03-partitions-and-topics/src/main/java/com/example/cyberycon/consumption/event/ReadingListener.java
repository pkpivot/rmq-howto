package com.example.cyberycon.consumption.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.cyberycon.consumption.readings.ReadingConsumer;

@Service
public class ReadingListener {
    private static Logger logger = LoggerFactory.getLogger(ReadingListener.class) ;
    private ReadingConsumer readingConsumer; 
    
    public ReadingListener(ReadingConsumer readingConsumer) { 
        this.readingConsumer = readingConsumer;

    }

    @RabbitListener(queues = "aggregator")
    public void listen(String msg) {
        logger.info(msg);
        readingConsumer.nextReading(msg);
    }

}
