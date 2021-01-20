package cc.oobootcamp.sample.parkingLot;


import java.util.List;
import java.util.Optional;

public interface ParkingBoy {
  public Ticket park(Car car);

  public Car pick(Ticket ticket);

  public List<ParkingLot> getParkingLots();

}
