package cc.oobootcamp.sample.parkingLot;

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

public interface ParkingLot {
  public Ticket park(Car car);

  public Car pickUp(Ticket ticket);

  public int getAvailableSpace();

  public double getAvailableRate();

  public int getTotalSpace();

  public String getNameTag();

  public void setAvailableSpace(int availableSpace);

  public ParkingReport.ReportLine getStatistics(Collector collector);

  public Boolean checkIfTicketInThisParkingLot(Ticket ticket);

  public boolean hasSameCarParked(Car car);
}