package com.example.cyberycon.smartmeter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class SmartMeterIntegrationTests {

	private CountDownLatch latch;

	@Autowired
	private Meter meter;

	@Autowired
	private TestListener listener;

	@Test
	public void loadContext() {
		assertNotNull(meter);
	}

	@Test
	public void shouldSendReadings() throws InterruptedException {
		latch = new CountDownLatch(3);
		listener.setLatch(latch);
		meter.start();
		assertTrue(latch.await(20, TimeUnit.SECONDS));
		meter.stop();
	}



}