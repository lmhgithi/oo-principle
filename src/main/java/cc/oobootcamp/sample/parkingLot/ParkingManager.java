package cc.oobootcamp.sample.parkingLot;

import cc.oobootcamp.sample.parkingLot.exceptions.NoParkingBoyException;
import cc.oobootcamp.sample.parkingLot.exceptions.RepeatedParkingException;
import java.util.List;
import java.util.Optional;

/**
 * 给parking manager一辆车，他手下有停车小弟，并且所有停车小弟的停车场都是空的时候
 * 停车
 * parking manager会按顺序叫第1个停车小弟去停车，并返回ticket
 *
 * 给parking manager一辆车，他手下有停车小弟，并且只有一个停车小弟的停车场还没停满
 * 停车
 * parking manager会叫还有空位的停车小弟去停车，并返回ticket
 *
 * 给parking manager一辆车，他手下有停车小弟，但所有停车小弟的停车场都是满的时候
 * 停车
 * 提示 所有车位已满
 *
 * 给parking manager一辆车，他手下没有停车小弟
 * 停车
 * 提示 没有停车小弟
 *
 * 给parking manager 一辆车，当车是相同的
 * 停车
 * 提示 这辆车已经停过了，重复停车
 *
 * ============================================
 *
 * 当我给 parking manager 一张票据，且票据是有效的，parking manager的停车小弟的停车场有对应的ticket
 * 取车
 * parking manager 会给我返回我的车
 *
 * 当我给 parking manager 一张票据，且票据是无效的
 * 取车
 * 提示 票据错误
 *
 * 当我给 parking manager 一张票据，且票据是有效的
 * 取两次车
 * 第一次取车成功。第二次取车失败，提示 票据错误
 */

public class ParkingManager {
  List<AbstractParkingBoy> parkingBoys;

  public ParkingManager(List<AbstractParkingBoy> parkingBoys) {
    this.parkingBoys = parkingBoys;
  }

  public Ticket park(Car car) {
    if(checkIfHasSameCarParked(car)) throw new RepeatedParkingException();
    return findAvailableParkingBoy()
        .map(parkingBoy -> parkingBoy.park(car))
        .orElse(null);
  }

  public Car pick(Ticket ticket) {
    Optional<AbstractParkingBoy> responsibleParkingBoy = findResponsibleParkingBoy(ticket);
    return responsibleParkingBoy.map(parkingBoy -> parkingBoy.pick(ticket))
        .orElse(null);
  }

  private Optional<AbstractParkingBoy> findAvailableParkingBoy() {
    if (parkingBoys.size() == 0) {
      throw new NoParkingBoyException();
    }
    return parkingBoys
        .stream()
        .filter(parkingBoy -> parkingBoy.findParkingLot().isPresent())
        .findFirst();
  }

  private Boolean checkIfHasSameCarParked(Car car) {
    return parkingBoys
        .stream()
        .anyMatch(parkingBoy -> parkingBoy.checkIfHasSameCarParked(car));
  }

  private Optional<AbstractParkingBoy> findResponsibleParkingBoy(Ticket ticket) {
    return parkingBoys
        .stream()
        .filter(parkingBoy -> parkingBoy
                .findAnyParkingLotTicketBelongTo(ticket)
                .isPresent())
        .findAny();

  }
}
