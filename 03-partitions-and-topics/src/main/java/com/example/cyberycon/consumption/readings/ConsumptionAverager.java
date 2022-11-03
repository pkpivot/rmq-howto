package com.example.cyberycon.consumption.readings;

import com.example.cyberycon.consumption.calculations.RollingAverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component 
public class ConsumptionAverager implements ReadingConsumer {

	private final Logger logger = LoggerFactory.getLogger(ConsumptionAverager.class);

	private Map<String,Integer> previousReadings = new HashMap<>();

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
		int consumptionDelta = getReadingDelta(readingParts);
		rollingAverage.addValue(consumptionDelta);
		logger.info("Latest average + " + Double.toString(rollingAverage.latestAverage()));
	}

	private int getReadingDelta(String[] readingParts) {
		try {
			int thisReading = Integer.parseInt(readingParts[2]);
			return delta(readingParts[0], thisReading);
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}

	private int delta(String meterId, int thisReading) {
		int previousReading = 0;
		if (!previousReadings.containsKey(meterId)) {
		}
		else {
			previousReading = previousReadings.get(meterId) ;
		}
		previousReadings.put(meterId, thisReading) ;
		return thisReading - previousReading ;

	}

	public double averageConsumption() {
		return rollingAverage.latestAverage();
	}
}
