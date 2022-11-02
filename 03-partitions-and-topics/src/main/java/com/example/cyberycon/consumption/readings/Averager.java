package com.example.cyberycon.consumption.readings;

import com.example.cyberycon.consumption.calculations.RollingAverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
public class ConsumptionAverager implements ReadingConsumer {

	private final Logger logger = LoggerFactory.getLogger(ConsumptionAverager.class);

	private RollingAverage rollingAverage; 
	
	public ConsumptionAverager(RollingAverage rollingAverage) {
		this.rollingAverage = rollingAverage; 
	}

	@Override
	public void nextReading(String reading) {
		String[] readingParts = reading.split(":");
		if (readingParts.length != 3) {
			throw new RuntimeException("Invalid reading") ; 
		}
		try {
			int readingValue = Integer.parseInt(readingParts[2]); 
			rollingAverage.addValue(readingValue);
			logger.info("Latest average + " + Double.toString(rollingAverage.latestAverage()));
		}
		catch (NumberFormatException e) {
			throw new RuntimeException (e) ; 
		}
		
	}
}
