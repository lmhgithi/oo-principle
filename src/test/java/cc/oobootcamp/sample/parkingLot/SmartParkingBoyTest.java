package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SmartParkingBoyTest {

  private SmartParkingBoy smartParkingBoy;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    this.smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
    }});
  }

  @Test
  void should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty() {
    Ticket ticket = smartParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_pick_up_car_when_only_one_parking_lot_has_most_available_spaces() {
    smartParkingBoy.park(new Car());
    smartParkingBoy.park(new Car());
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(10);

    Ticket ticket = smartParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(9);
  }

  @Test
  void should_pick_up_car_when_two_parking_lot_has_most_available_spaces() {
    smartParkingBoy.park(new Car());
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(10);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(10);

    Ticket ticket = smartParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(10);
  }

  @Test
  void should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full() {
    givenParkOneParkingLotToFull(smartParkingBoy);
    givenParkOneParkingLotToFull(smartParkingBoy);
    givenParkOneParkingLotToFull(smartParkingBoy);

    Ticket ticket = smartParkingBoy.park(new Car());

    assertThat(ticket).isEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(0);
  }


  @Test
  void should_pick_up_car() {
    Car carPark = new Car();
    Ticket ticket = smartParkingBoy.park(carPark);

    Car carPickUp = smartParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(carPark);
  }

  @Test
  void should_not_pick_up_car_when_ticket_not_valid() {
    Ticket ticket = new Ticket();

    Car carPickUp = smartParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(null);
  }

  @Test
  void should_not_pick_up_car_twice_using_same_ticket() {
    Ticket ticket = smartParkingBoy.park(new Car());

    Car carPickUpOnce = smartParkingBoy.pick(ticket);
    Car carPickUpTwice = smartParkingBoy.pick(ticket);

    assertThat(carPickUpOnce).isNotEqualTo(null);
    assertThat(carPickUpTwice).isEqualTo(null);
  }

  @Test
  void should_not_parked_same_car_twice() {
    Car car = new Car();

    Ticket ticket = smartParkingBoy.park(car);
    Ticket ticket2 = smartParkingBoy.park(car);

    assertThat(ticket).isNotEqualTo(null);
    assertThat(ticket2).isEqualTo(null);
  }

  private int getAvailableSpaceOfXParkingLot(int i) {
    return smartParkingBoy.getParkingLots().get(i).getAvailableSpace();
  }

  private void givenParkOneParkingLotToFull(SmartParkingBoy smartParkingBoy) {
    for (int i = 0; i < spacePerParkingLot; i++) {
      smartParkingBoy.park(new Car());
    }
  }
}