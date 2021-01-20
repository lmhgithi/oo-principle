package cc.oobootcamp.sample.parkingLot;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 给smart parking boy一辆车，当所有的停车场都是空的时候
 * 停车
 * smart parking boy会按顺序把车停在第一个停车场，并返回ticket，第一个车场可用车位减1
 *
 * 给smart parking boy一辆车，只有一个停车场有最多的停车位
 * 停车
 * smart parking boy会把车停在最少停车位的停车场，最少停车位的停车场可用车位减1
 *
 * 给smart parking boy一辆车，当有多个停车场都有相同的停车位，且比剩余停车场停车位多
 * 停车
 * smart parking boy会把车停在顺序最靠前的停车场，顺序最靠前的停车场可用车位减1
 *
 * 给smart parking boy一辆车，当所有停车场都停满车的时候
 * 停车
 * 提示 我负责的车位已满
 *
 * 给smart parking boy 一辆车，当车是相同的
 * 停车
 * 提示 这辆车已经停过了，重复停车
 *
 * ============================================
 *
 * 当我给 smart parking boy 一张票据，且票据是有效的，smart parking boy负责的车场中有对应的ticket
 * 取车
 * smart parking boy 会给我返回我的车
 *
 * 当我给 smart parking boy 一张票据，且票据是无效的
 * 取车
 * 提示 票据错误
 *
 * 当我给 smart parking boy 一张票据，且票据是有效的
 * 取两次车
 * 第一次取车成功。第二次取车失败，提示 票据错误
 */

public class SmartParkingBoy implements ParkingBoy {
  private final List<ParkingLot> parkingLots;

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    if (checkIfHasSameCarParked(car)) {
      return null;
    }

    Optional<ParkingLot> parkingLot = findFirstAvailableParkingLotHasMostSpaces();
    return parkingLot.map(lot -> lot.park(car)).orElse(null);
  }

  public Car pick(Ticket ticket) {
    Optional<ParkingLot> parkingLot = findAnyParkingLotTicketBelongTo(ticket);
    return parkingLot.map(lot -> lot.pickUp(ticket)).orElse(null);
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  private Optional<ParkingLot> findFirstAvailableParkingLotHasMostSpaces() {
    return parkingLots.stream()
        .filter(lot -> lot.getAvailableSpace() > 0)
        .max(Comparator.comparing(ParkingLot::getAvailableSpace));
  }

  private Optional<ParkingLot> findAnyParkingLotTicketBelongTo(Ticket ticket) {
    return parkingLots.stream()
        .filter(lot -> lot.checkIfTicketInThisParkingLot(ticket))
        .findAny();
  }

  private Boolean checkIfHasSameCarParked(Car car) {
    Optional<ParkingLot> any = parkingLots.stream().filter(parkingLot -> parkingLot.hasSameCarParked(car)).findAny();
    return any.isPresent();
  }
}
