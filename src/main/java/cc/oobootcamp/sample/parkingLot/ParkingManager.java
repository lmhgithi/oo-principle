package cc.oobootcamp.sample.parkingLot;

import cc.oobootcamp.sample.parkingLot.exceptions.NoParkingBoyException;
import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import cc.oobootcamp.sample.parkingLot.util.ParkingReportPrinter;
import cc.oobootcamp.sample.parkingLot.util.ReportGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 给parking manager一辆车，他手下有停车小弟，并且所有停车小弟的停车场都是空的时候
 * 停车
 * parking manager会按顺序叫第1个停车小弟去停车，并返回ticket
 * <p>
 * 给parking manager一辆车，他手下有停车小弟，并且只有一个停车小弟的停车场还没停满
 * 停车
 * parking manager会叫还有空位的停车小弟去停车，并返回ticket
 * <p>
 * 给parking manager一辆车，他手下有停车小弟，但所有停车小弟的停车场都是满的时候
 * 停车
 * 提示 所有车位已满
 * <p>
 * 给parking manager一辆车，他手下没有停车小弟
 * 停车
 * 提示 没有停车小弟
 * <p>
 * 给parking manager 一辆车，当车是相同的
 * 停车
 * 提示 这辆车已经停过了，重复停车
 * <p>
 * ============================================
 * <p>
 * 当我给 parking manager 一张票据，且票据是有效的，parking manager的停车小弟的停车场有对应的ticket
 * 取车
 * parking manager 会给我返回我的车
 * <p>
 * 当我给 parking manager 一张票据，且票据是无效的
 * 取车
 * 提示 票据错误
 * <p>
 * 当我给 parking manager 一张票据，且票据是有效的
 * 取两次车
 * 第一次取车成功。第二次取车失败，提示 票据错误
 */


/** <p>
 * 给parking manager 一辆车，当车是相同的
 * 让管理员自己停车
 * 提示 这辆车已经停过了，重复停车
 * <p>
 * 给manager 一辆车，让manager 去停车，manager负责的停车场有空位
 * 让管理员自己停车
 * 提示 停车成功，返回ticket
 * <p>
 * 给manager 一辆车，让manager 去停车，manager负责的停车场没有空位
 * 让管理员自己停车
 * 提示 所有车位已满
 * <p>
 *
 *   =====================================
 *
 * 给manager 一个Ticket，当Ticket对应的车在 manager 负责的停车场
 * 取车
 * 取车成功，返回ticket
 * <p>
 */
public class ParkingManager extends AbstractParkingPerson {
  private final List<AbstractParkingPerson> parkingBoys;

  public ParkingManager(List<AbstractParkingPerson> parkingBoys, List<ParkingLot> parkingLots) {
    super(parkingLots);
    this.parkingBoys = parkingBoys == null ? new ArrayList<AbstractParkingPerson>() : parkingBoys;
  }

  public Ticket parkByParkingBoy(Car car) {
    if (checkIfHasSameCarParked(car)) {
      throw new RepeatedParkingException();
    }

    return findAvailableParkingBoy()
        .map(parkingBoy -> parkingBoy.park(car))
        .orElse(null);
  }

  public Car pick(Ticket ticket) {
    Optional<AbstractParkingPerson> responsibleParkingBoy = findResponsibleParkingBoy(ticket);
    return responsibleParkingBoy
        .map(parkingBoy -> parkingBoy.pick(ticket))
        .orElse(super.pick(ticket));
  }

  public List<AbstractParkingPerson> getParkingBoys() {
    return parkingBoys;
  }

  @Override
  Optional<ParkingLot> findParkingLot() {
    return this.getParkingLots()
        .stream()
        .filter(parkingLot -> parkingLot.getAvailableSpace() > 0)
        .findFirst();
  }

  private Optional<AbstractParkingPerson> findAvailableParkingBoy() {
    if (parkingBoys == null || parkingBoys.isEmpty()) {
      throw new NoParkingBoyException();
    }
    return parkingBoys
        .stream()
        .filter(parkingBoy -> parkingBoy.findParkingLot().isPresent())
        .findFirst();
  }

  @Override
  public Boolean checkIfHasSameCarParked(Car car) {
    return parkingBoys
        .stream()
        .anyMatch(parkingBoy -> parkingBoy.checkIfHasSameCarParked(car))
        || this.getParkingLots()
        .stream()
        .anyMatch(parkingLot -> parkingLot.hasSameCarParked(car));
  }

  private Optional<AbstractParkingPerson> findResponsibleParkingBoy(Ticket ticket) {
    return parkingBoys
        .stream()
        .filter(parkingBoy -> parkingBoy
            .findAnyParkingLotTicketBelongTo(ticket)
            .isPresent())
        .findAny();

  }

  public int getAvailableSpaceOfAll() {
    return super.getAvailableSpace() +
        parkingBoys.stream()
            .mapToInt(AbstractParkingPerson::getAvailableSpace)
            .sum();
  }

  public int getTotalSpaceOfAll() {
    return super.getTotalSpace() +
        parkingBoys.stream()
            .mapToInt(AbstractParkingPerson::getTotalSpace)
            .sum();
  }

  public void getParkingReport(ParkingReportPrinter parkingReportPrinter) {
    ParkingReport parkingReport = ReportGenerator.generate(this);
    parkingReportPrinter.print(parkingReport);
  }
}
