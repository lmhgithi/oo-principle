package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cc.oobootcamp.sample.parkingLot.exceptions.NoParkingBoyException;
import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingManagerTest {

  private ParkingManager parkingManager;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    parkingManager = new ParkingManager(new ArrayList<AbstractParkingPerson>() {{
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
    }}, new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
    }});
  }

  @Test
  void should_park_when_has_parking_boys_and_space() {
    Ticket ticket = parkingManager.parkByParkingBoy(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_park_when_has_parking_boys_and_only_one_boys_lot_has_space() {
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();

    Ticket ticket = parkingManager.parkByParkingBoy(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_park_when_has_parking_boys_and_all_lots_are_full() {
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();
    givenParkOneBoyToFull();

    Ticket ticket = parkingManager.parkByParkingBoy(new Car());

    assertThat(ticket).isEqualTo(null);
  }

  @Test
  void should_throw_when_has_no_parking_boys() {
    parkingManager = new ParkingManager(new ArrayList<AbstractParkingPerson>(), new ArrayList<ParkingLot>());

    assertThrows(NoParkingBoyException.class, () -> parkingManager.parkByParkingBoy(new Car()));
  }

  @Test
  void should_throw_when_park_same_car_to_different_lot_of_different_parking_boy() {
    Car car = new Car();
    parkingManager.parkByParkingBoy(car);
    givenParkOneBoyToFull();

    assertThrows(RepeatedParkingException.class, () -> parkingManager.parkByParkingBoy(car));
  }


  @Test
  void should_pick_up_car() {
    Car carPark = new Car();
    Ticket ticket = parkingManager.parkByParkingBoy(carPark);

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
    Ticket ticket = parkingManager.parkByParkingBoy(new Car());

    Car carPickUpOnce = parkingManager.pick(ticket);
    Car carPickUpTwice = parkingManager.pick(ticket);

    assertThat(carPickUpOnce).isNotEqualTo(null);
    assertThat(carPickUpTwice).isEqualTo(null);
  }


  @Test
  void should_not_park_twice_with_manager() {
    givenParkOneParkingLotToFull();
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    assertThrows(RepeatedParkingException.class, () -> parkingManager.park(car));
    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_park_twice_with_manager_and_parking_boy() {
    givenParkOneParkingLotToFull();
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    assertThrows(RepeatedParkingException.class, () -> parkingManager.parkByParkingBoy(car));
    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_park_with_manager_when_all_parking_lot_is_not_full() {
    givenParkOneParkingLotToFull();

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_park_with_manager_when_all_parking_lot_is_full() {
    givenParkOneParkingLotToFull();
    givenParkOneParkingLotToFull();
    givenParkOneParkingLotToFull();

    Ticket ticket = parkingManager.park(new Car());

    assertThat(ticket).isEqualTo(null);
  }

  @Test
  void should_pick_up_car_when_car_in_managers_parking_lot() {
    Car carPark = new Car();
    Ticket ticket = parkingManager.park(carPark);

    Car carPickUp = parkingManager.pick(ticket);

    assertThat(carPickUp).isEqualTo(carPark);
  }

  private void givenParkOneBoyToFull() {
    for (int i = 0; i < spacePerParkingLot * 2; i++) {
      parkingManager.parkByParkingBoy(new Car());
    }
  }

  private void givenParkOneParkingLotToFull() {
    for (int i = 0; i < spacePerParkingLot; i++) {
      parkingManager.park(new Car());
    }
  }

}