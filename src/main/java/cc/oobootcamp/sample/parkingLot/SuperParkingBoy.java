package cc.oobootcamp.sample.parkingLot;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 给super parking boy一辆车，当所有的停车场都是空的时候
 * 停车
 * super parking boy会按顺序把车停在第一个停车场，并返回ticket，第一个车场可用车位减1
 *
 * 给super parking boy一辆车，只有一个停车场有最多的空置率
 * 停车
 * super parking boy会把车停在最高空置率的停车场，该停车场可用车位减1
 *
 * 给super parking boy一辆车，当有多个停车场都有相同的空置率，且比剩余停车场空置率多，并且这些有不同的停车位数
 * 停车
 * super parking boy会把车停在空置率最高的停车场中停车位数最多的一个，该停车场可用车位减1
 *
 * 给super parking boy一辆车，当有多个停车场都有相同的空置率，且比剩余停车场空置率多，并且这些有相同的停车位数
 * 停车
 * super parking boy会把车停在空置率最高、且停车位数最高的停车场中，顺序最靠前的一个，该停车场可用车位减1
 *
 * 给super parking boy一辆车，当所有停车场都停满车的时候
 * 停车
 * 提示 我负责的车位已满
 *
 * 给super parking boy 一辆车，当车是相同的
 * 停车
 * 提示 这辆车已经停过了，重复停车
 *
 * ============================================
 *
 * 当我给 super parking boy 一张票据，且票据是有效的，super parking boy负责的车场中有对应的ticket
 * 取车
 * super parking boy 会给我返回我的车
 *
 * 当我给 super parking boy 一张票据，且票据是无效的
 * 取车
 * 提示 票据错误
 *
 * 当我给 super parking boy 一张票据，且票据是有效的
 * 取两次车
 * 第一次取车成功。第二次取车失败，提示 票据错误
 */

public class SuperParkingBoy extends AbstractParkingBoy {

  public SuperParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<ParkingLot> findParkingLot() {
    return this.getParkingLots()
        .stream()
        .filter(lot -> lot.getAvailableSpace() > 0)
        .max(Comparator.comparing(ParkingLot::getAvailableRate).thenComparing(ParkingLot::getAvailableSpace));
  }
}
