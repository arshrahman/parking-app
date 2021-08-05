package strategy.parking;

import exception.ErrorMessage;
import exception.ParkingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AscendingOrderParkingStrategyTest {
    private final ParkingStrategy ascendingOrderParkingStrategy = new AscendingOrderParkingStrategy();

    @Test
    public void testValidAscendingOrderParkingStrategy() throws ParkingException {
        ascendingOrderParkingStrategy.createLots(List.of(2, 3));
        assertTrue(ascendingOrderParkingStrategy.isLotAvailable());
        ascendingOrderParkingStrategy.removeLot(2);
        assertEquals(ascendingOrderParkingStrategy.getLot(), 3);
        ascendingOrderParkingStrategy.addLot(1);
        assertEquals(ascendingOrderParkingStrategy.getLot(), 1);
        ascendingOrderParkingStrategy.removeLot(1);
        ascendingOrderParkingStrategy.removeLot(3);
        assertFalse(ascendingOrderParkingStrategy.isLotAvailable());

        Exception exception = assertThrows(ParkingException.class, ascendingOrderParkingStrategy::getLot);
        assertEquals(exception.getMessage(), ErrorMessage.PROCESSING_ERROR.getMessage());
    }
}
