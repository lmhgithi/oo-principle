package cc.oobootcamp.sample.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoyClient {

  private static GraduateParkingBoy graduateParkingBoy;
  private static final int spacePerParkingLot = 10;

  static void should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty() {
    System.out.println("=============在所有停车场都没停车时，在第一个停车场停车，并返回ticket================");
    graduateParkingBoy = new GraduateParkingBoy();

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_park_to_second_parking_lot_with_graduate_parking_boy_when_first_parking_is_full() {
    System.out.println("=============在前1个停车场停满时，在第2个停车场停车，并返回ticket================");
    graduateParkingBoy = new GraduateParkingBoy();

    parkOneParkingLotToFull(graduateParkingBoy);

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_park_to_third_parking_lot_with_graduate_parking_boy_when_first_and_second_parking_is_full() {
    System.out.println("=============在前2个停车场停满时，在第3个停车场停车，并返回ticket================");
    graduateParkingBoy = new GraduateParkingBoy();

    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full() {
    System.out.println("=============在所有停车场满的时候不能停车，且返回\"车位已满\"================");
    graduateParkingBoy = new GraduateParkingBoy();

    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_pick_up_car() {
    System.out.println("=============在ticket与任一个停车场中有对应车时，可以取车，并返回对应的car================");
    graduateParkingBoy = new GraduateParkingBoy();

    Car carPark = new Car();
    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(carPark);
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    System.out.println("graduate parking boy正在取车");
    Car carPickUp = graduateParkingBoy.pick(ticket);
    if (carPickUp == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  static void should_can_park_after_pick_up_car_when_all_parking_lot_is_full() {
    System.out.println("=============在所有停车场满的时候，取了一辆车后，可以再停一辆车================");
    graduateParkingBoy = new GraduateParkingBoy();

    parkOneParkingLotToFull(graduateParkingBoy);
    parkOneParkingLotToFull(graduateParkingBoy);
    List<Ticket> tickets = parkOneParkingLotToFull(graduateParkingBoy);
    printAvailableSpaceOfEachParkingLot();

    System.out.println("graduate parking boy正在取车");
    Car car = graduateParkingBoy.pick(tickets.get(0));
    if (car == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");
  }

  static void should_not_pick_up_car_when_ticket_not_valid() {
    System.out.println("=============取车时没有停车场与ticket有对应车，不可取车，返回\"票据错误\"================");
    graduateParkingBoy = new GraduateParkingBoy();
    System.out.println("生成了一张假票据");
    Ticket ticket = new Ticket();

    System.out.println("graduate parking boy正在用假票据取车");
    Car carPickUp = graduateParkingBoy.pick(ticket);

    if (carPickUp == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  static void should_not_pick_up_car_twice_using_same_ticket() {
    System.out.println("=============同一个票据取两次，第一次成功取车，第二次不可取车，返回\"票据错误\"================");
    graduateParkingBoy = new GraduateParkingBoy();

    System.out.println("graduate parking boy正在停车");
    Ticket ticket = graduateParkingBoy.park(new Car());
    if (ticket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");

    System.out.println("graduate parking boy正在第一次取车");
    Car carPickUpOnce = graduateParkingBoy.pick(ticket);
    if (carPickUpOnce == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }

    System.out.println("graduate parking boy正在第二次取车");
    Car carPickUpTwice = graduateParkingBoy.pick(ticket);
    if (carPickUpTwice == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  private static void printAvailableSpaceOfEachParkingLot() {
    for (ParkingLot parkingLot : graduateParkingBoy.getParkingLots()) {
      System.out.println("停车场剩余车位： " + parkingLot.getAvailableSpace());
    }
  }

  private static List<Ticket> parkOneParkingLotToFull(GraduateParkingBoy graduateParkingBoy) {
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < spacePerParkingLot; i++) {
      Ticket ticket = graduateParkingBoy.park(new Car());
      tickets.add(ticket);
    }
    return tickets;
  }

  public static void main(String[] args) {
    should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty();
    should_park_to_second_parking_lot_with_graduate_parking_boy_when_first_parking_is_full();
    should_park_to_third_parking_lot_with_graduate_parking_boy_when_first_and_second_parking_is_full();
    should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full();

    should_pick_up_car();
    should_can_park_after_pick_up_car_when_all_parking_lot_is_full();
    should_not_pick_up_car_when_ticket_not_valid();
    should_not_pick_up_car_twice_using_same_ticket();
  }
}