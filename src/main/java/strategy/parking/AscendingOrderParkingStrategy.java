package strategy.parking;

import exception.ErrorMessage;
import exception.ParkingException;

import java.util.List;
import java.util.TreeSet;

public class AscendingOrderParkingStrategy implements ParkingStrategy {

    TreeSet<Integer> lots;

    public AscendingOrderParkingStrategy() {
        this.lots = new TreeSet<>();
    }

    @Override
    public boolean createLots(List<Integer> ids) {
        lots.clear();
        return lots.addAll(ids);
    }

    @Override
    public boolean removeLot(int id) {
        return lots.remove(id);
    }

    @Override
    public boolean addLot(int id) {
        return lots.add(id);
    }

    @Override
    public boolean isLotAvailable() {
        return !lots.isEmpty();
    }

    @Override
    public int getLot() throws ParkingException {
        if (isLotAvailable())
            return lots.first();
        throw new ParkingException(ErrorMessage.PROCESSING_ERROR);
    }
}
