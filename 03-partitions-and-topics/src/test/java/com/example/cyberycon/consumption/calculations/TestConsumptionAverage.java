package com.example.cyberycon.consumption.calculations;

import com.example.cyberycon.consumption.readings.ConsumptionAverager;
import org.junit.jupiter.api.Test;

import java.lang.invoke.ConstantBootstraps;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConsumptionAverage {

    @Test
    public void testConsumptionAverageSingleMeter() {
        RollingAverage rollingAverage = new RollingAverageImpl() ;
        ConsumptionAverager averager = new ConsumptionAverager(rollingAverage);
        averager.nextReading(makeReading(10));
        averager.nextReading(makeReading(20));
        averager.nextReading(makeReading(20));
        averager.nextReading(makeReading(40));
        assertEquals(10, averager.averageConsumption()) ;


    }

    @Test
    public void testConsumptionAverageMultiMeter() {
        RollingAverage rollingAverage = new RollingAverageImpl() ;
        ConsumptionAverager averager = new ConsumptionAverager(rollingAverage);
        averager.nextReading(makeReading(30, "meter-2"));
        averager.nextReading(makeReading(10, "meter-1"));
        averager.nextReading(makeReading(20, "meter-1"));
        averager.nextReading(makeReading(50, "meter-1"));
        averager.nextReading(makeReading(40, "meter-2"));
        averager.nextReading(makeReading(40, "meter-2"));
        averager.nextReading(makeReading(60, "meter-1"));
        averager.nextReading(makeReading(40, "meter-2"));
        averager.nextReading(makeReading(40, "meter-2"));
        averager.nextReading(makeReading(60, "meter-2"));
        assertEquals(12, averager.averageConsumption()) ;


    }

    private String makeReading(int reading) {
        return makeReading(reading, "meter-1");
    }

    private String makeReading(int reading, String meterId) {
        return String.format("%s:%d:%d", meterId, new Date().getTime(), reading);
    }


}
