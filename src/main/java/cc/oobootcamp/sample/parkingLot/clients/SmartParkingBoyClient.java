package cc.oobootcamp.sample.parkingLot.clients;

import cc.oobootcamp.sample.parkingLot.Car;
import cc.oobootcamp.sample.parkingLot.NormalParkingLot;
import cc.oobootcamp.sample.parkingLot.ParkingLot;
import cc.oobootcamp.sample.parkingLot.SmartParkingBoy;
import cc.oobootcamp.sample.parkingLot.Ticket;
import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyClient {

  private static SmartParkingBoy smartParkingBoy;
  private static final int spacePerParkingLot = 10;

  static void should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty() {
    System.out.println("=============在所有停车场都没停车时，在第一个停车场停车，并返回ticket================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_pick_up_car_when_only_one_parking_lot_has_most_available_spaces() {
    System.out.println("=============在有第三个停车场有最多车位时，停到第三个停车场，并返回ticket================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});
    smartParkingBoy.park(new Car());
    smartParkingBoy.park(new Car());
    printAvailableSpaceOfEachParkingLot();

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_pick_up_car_when_two_parking_lot_has_most_available_spaces() {
    System.out.println("=============在有第2.3个停车场都有最多车位时，停到第2个停车场，并返回ticket================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});
    smartParkingBoy.park(new Car());
    printAvailableSpaceOfEachParkingLot();

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full() {
    System.out.println("=============在所有停车场满的时候不能停车，且返回\"车位已满\"================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});

    parkOneParkingLotToFull(smartParkingBoy);
    parkOneParkingLotToFull(smartParkingBoy);
    parkOneParkingLotToFull(smartParkingBoy);

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    printAvailableSpaceOfEachParkingLot();
  }

  static void should_pick_up_car() {
    System.out.println("=============在ticket与任一个停车场中有对应车时，可以取车，并返回对应的car================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});

    Car carPark = new Car();
    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(carPark);
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    System.out.println("smart parking boy正在取车");
    Car carPickUp = smartParkingBoy.pick(ticket);
    if (carPickUp == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  static void should_can_park_after_pick_up_car_when_all_parking_lot_is_full() {
    System.out.println("=============在所有停车场满的时候，取了一辆车后，可以再停一辆车================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});

    parkOneParkingLotToFull(smartParkingBoy);
    parkOneParkingLotToFull(smartParkingBoy);
    List<Ticket> tickets = parkOneParkingLotToFull(smartParkingBoy);
    printAvailableSpaceOfEachParkingLot();

    System.out.println("smart parking boy正在取车");
    Car car = smartParkingBoy.pick(tickets.get(0));
    if (car == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }
  }

  static void should_not_pick_up_car_when_ticket_not_valid() {
    System.out.println("=============取车时没有停车场与ticket有对应车，不可取车，返回\"票据错误\"================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});
    System.out.println("生成了一张假票据");
    Ticket ticket = new Ticket();

    System.out.println("smart parking boy正在用假票据取车");
    Car carPickUp = smartParkingBoy.pick(ticket);

    if (carPickUp == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  static void should_not_pick_up_car_twice_using_same_ticket() {
    System.out.println("=============同一个票据取两次，第一次成功取车，第二次不可取车，返回\"票据错误\"================");
    smartParkingBoy = new SmartParkingBoy(new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot());
      add(new NormalParkingLot());
      add(new NormalParkingLot());
    }});

    System.out.println("smart parking boy正在停车");
    Ticket ticket = smartParkingBoy.park(new Car());
    if (ticket != null) {
      System.out.println("已成功停车，并获得票据");
    } else {
      System.out.println("车位已满");
    }

    System.out.println("smart parking boy正在第一次取车");
    Car carPickUpOnce = smartParkingBoy.pick(ticket);
    if (carPickUpOnce == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }

    System.out.println("smart parking boy正在第二次取车");
    Car carPickUpTwice = smartParkingBoy.pick(ticket);
    if (carPickUpTwice == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  private static void printAvailableSpaceOfEachParkingLot() {
    for (ParkingLot parkingLot : smartParkingBoy.getParkingLots()) {
      System.out.println("停车场剩余车位： " + parkingLot.getAvailableSpace());
    }
  }

  private static List<Ticket> parkOneParkingLotToFull(SmartParkingBoy smartParkingBoy) {
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < spacePerParkingLot; i++) {
      Ticket ticket = smartParkingBoy.park(new Car());
      tickets.add(ticket);
    }
    return tickets;
  }

  public static void main(String[] args) {
    should_park_with_graduate_parking_boy_when_all_parking_lots_are_empty();
    should_pick_up_car_when_only_one_parking_lot_has_most_available_spaces();
    should_pick_up_car_when_two_parking_lot_has_most_available_spaces();
    should_not_park_with_graduate_parking_boy_when_all_parking_lot_is_full();

    should_pick_up_car();
    should_can_park_after_pick_up_car_when_all_parking_lot_is_full();
    should_not_pick_up_car_when_ticket_not_valid();
    should_not_pick_up_car_twice_using_same_ticket();
  }
}