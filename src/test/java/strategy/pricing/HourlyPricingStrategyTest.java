package strategy.pricing;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourlyPricingStrategyTest {
    private final PricingStrategy hourlyPricingStrategy = new HourlyPricingStrategy(2);

    @Test
    public void testValidHourlyPricingStrategy() {
        final long twoHoursInSeconds = 2 * 3600;
        Instant start = Instant.now().minusSeconds(twoHoursInSeconds);
        Instant end = Instant.now();
        assertEquals(hourlyPricingStrategy.calculateFare(start, end), 4.0);
    }
}
