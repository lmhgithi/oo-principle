package cc.oobootcamp.sample.parkingLot;

import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * - 停车场可以停车，成功时返回停车号码
 *      停车场空的时候：成功时返回停车号码
 *      停车场已经停了多辆车（还没满）：成功时返回停车号码
 *      连续停车成功
 * - 在满的时候不能停车，且返回"车位已满"
 * - 停车场可以按号码取车，返回停车号码
 *      停车场只停了我一辆车的时候：成功时返回停车号码
 *      停车场已经停了多辆车：成功时返回停车号码
 * - 取车时对应号码上没有车，不可取车，返回"票据错误"
 */

public class NormalParkingLot implements ParkingLot{
  private final Map<Ticket, Car> ticketCarMap = new HashMap<>();
  private int availableSpace;
  private final int totalSpace;

  public NormalParkingLot() {
    this.availableSpace = 10;
    this.totalSpace = 10;
  }

  public NormalParkingLot(int availableSpace) {
    this.availableSpace = availableSpace;
    this.totalSpace = availableSpace;
  }

  public Ticket park(Car car) {
    if (hasSameCarParked(car))
      throw new RepeatedParkingException();

    if (hasAvailableSpace()) {
      Ticket ticket = new Ticket();
      ticketCarMap.put(ticket, car);
      availableSpace -= 1;
      return ticket;
    }

    return null;
  }

  public Car pickUp(Ticket ticket) {
    Car car = ticketCarMap.get(ticket);
    if (car != null) {
      ticketCarMap.remove(ticket);
      availableSpace += 1;
    }

    return car;
  }

  public Boolean checkIfTicketInThisParkingLot(Ticket ticket) {
    return ticketCarMap.containsKey(ticket);
  }

  public boolean hasSameCarParked(Car car) {
    Collection<Car> values = ticketCarMap.values();
    return values.contains(car);
  }

  public int getAvailableSpace() {
    return availableSpace;
  }

  public int getTotalSpace() {
    return totalSpace;
  }

  public void setAvailableSpace(int availableSpace) {
    this.availableSpace = availableSpace;
  }

  private boolean hasAvailableSpace() {
    return availableSpace > 0;
  }

  public double getAvailableRate() {
    return (double) availableSpace / (double) totalSpace;
  }
}