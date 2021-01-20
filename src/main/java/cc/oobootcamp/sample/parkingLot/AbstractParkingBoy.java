package cc.oobootcamp.sample.parkingLot;


import java.util.List;
import java.util.Optional;

public abstract class AbstractParkingBoy {
  private final List<ParkingLot> parkingLots;

  public AbstractParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    if (checkIfHasSameCarParked(car)) {
      return null;
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

  private Optional<ParkingLot> findAnyParkingLotTicketBelongTo(Ticket ticket) {
    return parkingLots.stream()
        .filter(lot -> lot.checkIfTicketInThisParkingLot(ticket))
        .findAny();
  }

  abstract Optional<ParkingLot> findParkingLot();

  private Boolean checkIfHasSameCarParked(Car car) {
    Optional<ParkingLot> any = parkingLots.stream().filter(parkingLot -> parkingLot.hasSameCarParked(car)).findAny();
    return any.isPresent();
  }

}
