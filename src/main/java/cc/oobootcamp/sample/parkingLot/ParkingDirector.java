package cc.oobootcamp.sample.parkingLot;

import cc.oobootcamp.sample.parkingLot.util.DefaultParkingReportPrinter;
import cc.oobootcamp.sample.parkingLot.util.ParkingReportPrinter;

/**
 * 给一个parking director,
 * 当director查看报表，
 * 报表显示parking manager管理的停车场，以及manager手下的parking boy，及parking boy所管理的停车场
 */
public class ParkingDirector {

  public static void getParkingReport(ParkingManager parkingManager) {
    parkingManager.getParkingReport(new DefaultParkingReportPrinter());
  }

  public static void getParkingReport(ParkingManager parkingManager, ParkingReportPrinter reportPrinter) {
    parkingManager.getParkingReport(reportPrinter);
  }
}
