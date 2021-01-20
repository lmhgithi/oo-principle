package cc.oobootcamp.sample.parkingLot;


import java.util.List;
import java.util.Optional;

/**
 * - GraduateParkingBoy停车，given car
 *    在所有停车场都没停车时，在第一个停车场停车，并返回ticket
 *    在第n个停车场没满时，不可以停到n+1个停车场
 *    在前n个停车场停满时，在第n+1个停车场停车，并返回ticket
 *      停多辆车时，刚好把第n个停车场停满了，在第n+1个停车场停车，并返回ticket
 *      前n个停车场已经停满时，在第n+1个停车场停车，并返回ticket
 *    在所有停车场满的时候不能停车，且返回"车位已满"
 *
 * - GraduateParkingBoy取车，given ticket
 *    在ticket与任一个停车场中有对应车时，可以取车，并返回对应的car
 *      在所有停车场满的时候，取了一辆车后，可以再停一辆车
 *    取车时没有停车场与ticket有对应车，不可取车，返回"票据错误"
 *      同一个票据取两次，第一次成功取车，第二次不可取车，返回"票据错误"
 */

public class GraduateParkingBoy extends AbstractParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<ParkingLot> findParkingLot() {
    return this.getParkingLots()
        .stream()
        .filter(parkingLot -> parkingLot.getAvailableSpace() > 0)
        .findFirst();
  }

}
