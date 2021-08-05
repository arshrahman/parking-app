package strategy.pricing;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.MINUTES;

public class HourlyPricingStrategy implements PricingStrategy {
    final private double rate;

    public HourlyPricingStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFare(Instant start, Instant end) {
        return (Math.ceil(MINUTES.between(start, end) / 60.0) * rate);
    }
}
