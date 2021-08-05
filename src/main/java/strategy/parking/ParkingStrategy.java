package strategy.parking;

import exception.ParkingException;

import java.util.List;

public interface ParkingStrategy {

    boolean createLots(List<Integer> ids);
    boolean removeLot(int id);
    boolean addLot(int id);
    boolean isLotAvailable();
    int getLot() throws ParkingException;
}
