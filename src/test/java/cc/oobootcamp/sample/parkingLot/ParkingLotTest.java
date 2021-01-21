package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ParkingLotTest {

  private ParkingLot parkingLot;

  @BeforeEach
  void beforeEach() {
    this.parkingLot = new NormalParkingLot();
  }

  @Test
  void should_can_park_when_parking_lot_is_empty() {
    Ticket ticket = parkingLot.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_can_park_when_parking_lot_is_not_empty() {
    parkingLot.park(new Car());
    parkingLot.park(new Car());

    Ticket ticket = parkingLot.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_can_park_multiple_times_when_parking_lot_is_not_full() {
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    parkingLot.park(new Car());

    Ticket ticket = parkingLot.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_park_when_parking_lot_is_full() {
    int parkingLotTotalSpace = 100;
    for (int i = 0; i < parkingLotTotalSpace; i++) {
      parkingLot.park(new Car());
    }

    Ticket ticket = parkingLot.park(new Car());

    assertThat(ticket).isEqualTo(null);
  }

  @Test
  void should_pick_up_the_car_when_the_position_is_parked() {
    Ticket ticket = parkingLot.park(new Car());

    Car car = parkingLot.pickUp(ticket);

    assertThat(car).isNotEqualTo(null);
  }

  @Test
  void should_pick_up_the_car_when_the_position_is_parked_and_multiple_car_is_parked() {
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    Ticket ticket = parkingLot.park(new Car());

    Car car = parkingLot.pickUp(ticket);

    assertThat(car).isNotEqualTo(null);
  }

  @Test
  void should_not_pick_up_the_car_when_the_position_is_not_parked() {
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    Ticket ticket = new Ticket();

    Car car = parkingLot.pickUp(ticket);

    assertThat(car).isEqualTo(null);
  }

  @Test
  void should_not_parked_same_car_twice() {
    Car car = new Car();
    Ticket ticket = parkingLot.park(car);

    assertThat(ticket).isNotEqualTo(null);
    assertThrows(RepeatedParkingException.class, () -> parkingLot.park(car));
  }

  @Test
  void should_pick_up_using_other_parking_lots_ticket() {
    ParkingLot parkingLot2 = new NormalParkingLot();
    Ticket ticket = parkingLot.park(new Car());
    Ticket ticket2 = parkingLot2.park(new Car());

    Car carPickUp = parkingLot2.pickUp(ticket);
    Car carPickUp2 = parkingLot.pickUp(ticket2);

    assertThat(carPickUp).isEqualTo(null);
    assertThat(carPickUp2).isEqualTo(null);
  }
}