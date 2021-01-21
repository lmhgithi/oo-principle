package cc.oobootcamp.sample.parkingLot;


import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractParkingPerson {
  private final List<ParkingLot> parkingLots;

  public AbstractParkingPerson(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots == null ? new ArrayList<ParkingLot>() : parkingLots;
  }

  public Ticket park(Car car) {
    if (checkIfHasSameCarParked(car)) {
      throw new RepeatedParkingException();
    }

    Optional<ParkingLot> parkingLot = findParkingLot();
    return parkingLot.map(lot -> lot.park(car)).orElse(null);
  }

  public Car pick(Ticket ticket) {
    Optional<ParkingLot> parkingLot = findAnyParkingLotTicketBelongTo(ticket);
    return parkingLot.map(lot -> lot.pickUp(ticket)).orElse(null);
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public Optional<ParkingLot> findAnyParkingLotTicketBelongTo(Ticket ticket) {
    return parkingLots.stream()
        .filter(lot -> lot.checkIfTicketInThisParkingLot(ticket))
        .findAny();
  }

  abstract Optional<ParkingLot> findParkingLot();

  public Boolean checkIfHasSameCarParked(Car car) {
    Optional<ParkingLot> any = parkingLots.stream().filter(parkingLot -> parkingLot.hasSameCarParked(car)).findAny();
    return any.isPresent();
  }

}
