package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import cc.oobootcamp.sample.parkingLot.exceptions.NoParkingBoyException;
import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingManagerTest {

  private ParkingManager parkingManager;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    parkingManager = new ParkingManager(new ArrayList<AbstractParkingBoy>() {{
      add(new GraduateParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
      add(new SmartParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
      add(new SuperParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
    }});
  }

  @Test
  void should_park_when_has_parking_boys_and_space() {
    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_park_when_has_parking_boys_and_only_one_boys_lot_has_space() {
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_park_when_has_parking_boys_and_all_lots_are_full() {
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isEqualTo(null);
  }

  @Test
  void should_throw_when_has_no_parking_boys() {
    parkingManager = new ParkingManager(new ArrayList<AbstractParkingBoy>());

    assertThrows(NoParkingBoyException.class, () -> parkingManager.park(new Car()));
  }

  @Test
  void should_throw_when_park_same_car_to_different_lot_of_different_parking_boy() {
    Car car = new Car();
    parkingManager.park(car);
    givenParkOneBoyToFull();

    assertThrows(RepeatedParkingException.class, () -> parkingManager.park(car));
  }


  @Test
  void should_pick_up_car() {
    Car carPark = new Car();
    Ticket ticket = parkingManager.park(carPark);

    Car carPickUp = parkingManager.pick(ticket);

    assertThat(carPickUp).isEqualTo(carPark);
  }

  @Test
  void should_not_pick_up_car_when_ticket_not_valid() {
    Ticket ticket = new Ticket();

    Car carPickUp = parkingManager.pick(ticket);

    assertThat(carPickUp).isEqualTo(null);
  }

  @Test
  void should_not_pick_up_car_twice_using_same_ticket() {
    Ticket ticket = parkingManager.park(new Car());

    Car carPickUpOnce = parkingManager.pick(ticket);
    Car carPickUpTwice = parkingManager.pick(ticket);

    assertThat(carPickUpOnce).isNotEqualTo(null);
    assertThat(carPickUpTwice).isEqualTo(null);
  }


  private void givenParkOneBoyToFull() {
    for (int i = 0; i < spacePerParkingLot * 2; i++) {
      parkingManager.park(new Car());
    }

  }

}