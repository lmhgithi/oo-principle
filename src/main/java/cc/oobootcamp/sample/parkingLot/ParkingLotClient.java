package cc.oobootcamp.sample.parkingLot;

public class ParkingLotClient {
  private static void should_not_pick_up_car_when_ticket_not_valid() {
    Car pickUpCar;
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    System.out.println("============取车时对应号码上没有车，不可取车，返回\"找不到车\"=================");
    System.out.println("生成了一张假票据");
    parkingTicket = new Ticket();
    normalParkingLot = new NormalParkingLot();

    System.out.println("正在使用假票据取车");
    pickUpCar = normalParkingLot.pickUp(parkingTicket);

    if (pickUpCar == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  private static void should_pick_up_car_when_multiple_car_parked() {
    Car pickUpCar;
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    Car carToPark;
    System.out.println("============停车场停了多辆车的时候，可以成功取车=================");
    normalParkingLot = new NormalParkingLot();
    carToPark = new Car();
    normalParkingLot.park(carToPark);
    normalParkingLot.park(carToPark);

    System.out.println("正在停车");
    parkingTicket = normalParkingLot.park(carToPark);
    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");

    System.out.println("正在使用票据取车");
    pickUpCar = normalParkingLot.pickUp(parkingTicket);

    if (pickUpCar == null) {
      System.out.println("票据错误");
    } else {
      System.out.println("已成功取车");
    }
  }

  private static void should_pick_up_car_when_one_car_parked() {
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    Car carToPark;
    System.out.println("============停车场只停了我的一辆车的时候，可以成功取车=================");
    normalParkingLot = new NormalParkingLot();
    carToPark = new Car();

    System.out.println("正在停车");
    parkingTicket = normalParkingLot.park(carToPark);
    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");

    System.out.println("正在使用票据取车");
    Car pickUpCar = normalParkingLot.pickUp(parkingTicket);

    if (pickUpCar == null) {
      System.out.println("票据错误");
    }
    System.out.println("已成功取车");
  }

  private static void should_not_park_when_full() {
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    Car carToPark;
    System.out.println("============在满的时候不能停车，且返回\"车位已满\"=================");
    normalParkingLot = new NormalParkingLot();
    carToPark = new Car();
    for (int i = 0; i < 100; i++) {
      normalParkingLot.park(carToPark);
    }

    System.out.println("正在停车");
    normalParkingLot.park(carToPark);
    parkingTicket = normalParkingLot.park(carToPark);

    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");
  }

  private static void should_can_multiple_parking() {
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    Car carToPark;
    System.out.println("============连续成功停车=================");
    normalParkingLot = new NormalParkingLot();
    carToPark = new Car();
    normalParkingLot.park(carToPark);
    normalParkingLot.park(carToPark);

    System.out.println("正在停车");
    parkingTicket = normalParkingLot.park(carToPark);

    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");
  }

  private static void should_park_when_parking_lot_not_empty() {
    Ticket parkingTicket;
    NormalParkingLot normalParkingLot;
    Car carToPark;
    System.out.println("============停车场有车（但没满）的时候，可以成功停车================");
    normalParkingLot = new NormalParkingLot();
    carToPark = new Car();

    System.out.println("正在停车");
    normalParkingLot.park(carToPark);
    parkingTicket = normalParkingLot.park(carToPark);

    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");
  }

  private static void should_park_when_parking_lot_empty() {
    System.out.println("=============停车场空的时候，可以成功停车================");
    NormalParkingLot normalParkingLot = new NormalParkingLot();
    Car carToPark = new Car();

    System.out.println("正在停车");
    Ticket parkingTicket = normalParkingLot.park(carToPark);

    if (parkingTicket != null)
      System.out.println("已成功停车，并获得票据");
    else
      System.out.println("车位已满");
  }


  public static void main(String[] args) {
    should_park_when_parking_lot_empty();
    should_park_when_parking_lot_not_empty();
    should_can_multiple_parking();
    should_not_park_when_full();


    should_pick_up_car_when_one_car_parked();
    should_pick_up_car_when_multiple_car_parked();
    should_not_pick_up_car_when_ticket_not_valid();

  }
}