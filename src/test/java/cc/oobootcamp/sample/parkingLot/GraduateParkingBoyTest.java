package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraduateParkingBoyTest {

  private GraduateParkingBoy graduateParkingBoy;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    this.graduateParkingBoy = new GraduateParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});
  }

  @Test
  void should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty() {
    Ticket ticket = graduateParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_park_to_second_parking_lot_with_graduate_parking_boy_when_first_parking_is_full() {
    parkOneParkingLotToFull(graduateParkingBoy);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(9);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(10);
  }

  @Test
  void should_park_to_third_parking_lot_with_graduate_parking_boy_when_first_and_second_parking_is_full() {
    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertThat(ticket).isNotEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(9);
  }

  @Test
  void should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full() {
    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertThat(ticket).isEqualTo(null);
    assertThat(getAvailableSpaceOfXParkingLot(0)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(1)).isEqualTo(0);
    assertThat(getAvailableSpaceOfXParkingLot(2)).isEqualTo(0);
  }

  @Test
  void should_pick_up_car() {
    Car carPark = new Car();
    Ticket ticket = graduateParkingBoy.park(carPark);

    Car carPickUp = graduateParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(carPark);
  }

  @Test
  void should_can_park_after_pick_up_car_when_all_parking_lot_is_full() {
    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);
    List<Ticket> tickets = parkOneParkingLotToFull(graduateParkingBoy);

    graduateParkingBoy.pick(tickets.get(0));

    Ticket ticket = graduateParkingBoy.park(new Car());
    assertThat(ticket).isNotEqualTo(null);
  }

  @Test
  void should_not_pick_up_car_when_ticket_not_valid() {
    Ticket ticket = new Ticket();

    Car carPickUp = graduateParkingBoy.pick(ticket);

    assertThat(carPickUp).isEqualTo(null);
  }

  @Test
  void should_not_pick_up_car_twice_using_same_ticket() {
    Ticket ticket = graduateParkingBoy.park(new Car());

    Car carPickUpOnce = graduateParkingBoy.pick(ticket);
    Car carPickUpTwice = graduateParkingBoy.pick(ticket);

    assertThat(carPickUpOnce).isNotEqualTo(null);
    assertThat(carPickUpTwice).isEqualTo(null);
  }


  private int getAvailableSpaceOfXParkingLot(int i) {
    return graduateParkingBoy.getParkingLots().get(i).getAvailableSpace();
  }

  private List<Ticket> parkOneParkingLotToFull(GraduateParkingBoy graduateParkingBoy) {
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < spacePerParkingLot; i++) {
      Ticket ticket = graduateParkingBoy.park(new Car());
      tickets.add(ticket);
    }
    return tickets;
  }
}