package strategy.pricing;

import java.time.Instant;

public interface PricingStrategy {
    double calculateFare(Instant start, Instant end);
}
