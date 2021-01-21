package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuperParkingBoyTest {

  private SuperParkingBoy superParkingBoy;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    this.superParkingBoy = new SuperParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
    }});
  }

  @Test
  void should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty() {
    Ticket ticket = superParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_park_car_to_highest_available_rate_parking_lot_when_only_one_parking_lot_has_most_available_rate() {
    this.superParkingBoy = new SuperParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(100));
      add(new NormalParkingLot(10));
    }});
    superParkingBoy.getParkingLots().get(0).setAvailableSpace(50);

    Ticket ticket = superParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(50);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(9);
  }

  @Test
  void should_park_car_to_highest_available_spaces_parking_lot_when_two_parking_lot_has_most_available_rate() {
    this.superParkingBoy = new SuperParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(10));
      add(new NormalParkingLot(100));
    }});
    superParkingBoy.getParkingLots().get(0).setAvailableSpace(5);
    superParkingBoy.getParkingLots().get(1).setAvailableSpace(50);

    Ticket ticket = superParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(5);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(49);
  }

  @Test
  void should_park_car_to_prior_parking_lot_when_two_parking_lot_has_most_available_rate_and_available_spaces() {
    this.superParkingBoy = new SuperParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(100));
      add(new NormalParkingLot(100));
    }});
    superParkingBoy.getParkingLots().get(0).setAvailableSpace(50);
    superParkingBoy.getParkingLots().get(1).setAvailableSpace(50);

    Ticket ticket = superParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(49);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(50);
  }

  @Test
  void should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full() {
    givenParkOneParkingLotToFull(superParkingBoy);
    givenParkOneParkingLotToFull(superParkingBoy);
    givenParkOneParkingLotToFull(superParkingBoy);

    Ticket ticket = superParkingBoy.park(new Car());

    assertThat(ticket).isEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(0);
  }


  @Test
  void should_pick_up_car() {
    Car carPark = new Car();
    Ticket ticket = superParkingBoy.park(carPark);

    Car carPickUp = superParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(carPark);
  }

  @Test
  void should_not_pick_up_car_when_ticket_not_valid() {
    Ticket ticket = new Ticket();

    Car carPickUp = superParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(null);
  }

  @Test
  void should_not_pick_up_car_twice_using_same_ticket() {
    Ticket ticket = superParkingBoy.park(new Car());

    Car carPickUpOnce = superParkingBoy.pick(ticket);
    Car carPickUpTwice = superParkingBoy.pick(ticket);

    assertThat(carPickUpOnce).isNotEqualTo(null);
    assertThat(carPickUpTwice).isEqualTo(null);
  }

  @Test
  void should_not_parked_same_car_twice() {
    Car car = new Car();
    Ticket ticket = superParkingBoy.park(car);

    assertThrows(RepeatedParkingException.class, () -> superParkingBoy.park(car));
    assertThat(ticket).isNotEqualTo(null);
  }

  private int getAvailableSpaceOfXParkingLot(int i) {
    return superParkingBoy.getParkingLots().get(i).getAvailableSpace();
  }

  private void givenParkOneParkingLotToFull(SuperParkingBoy superParkingBoy) {
    for (int i = 0; i < spacePerParkingLot; i++) {
      superParkingBoy.park(new Car());
    }
  }
}