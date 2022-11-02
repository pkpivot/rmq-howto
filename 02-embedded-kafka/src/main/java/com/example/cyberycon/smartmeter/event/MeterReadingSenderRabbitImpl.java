package com.example.cyberycon.smartmeter.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MeterReadingSenderRabbitImpl implements MeterReadingSender {

	private static Logger logger = LoggerFactory.getLogger(MeterReadingSender.class);

	private RabbitTemplate rabbitTemplate;

	@Value("${meter.topic}")
	private String topic ;

	@Value ("${meter.area}")
	private String area ;

	public MeterReadingSenderRabbitImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
	public void sendReading(String meterId, long timestamp, int reading) {
		String meterReading = String.format("%s:%d:%d", meterId, timestamp, reading);
		Message msg = new Message(meterReading.getBytes());
		logger.info(String.format("Sending %s", meterReading));
		rabbitTemplate.send(topic, area, msg);
	}

}
