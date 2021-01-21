package cc.oobootcamp.sample.parkingLot.util;

import cc.oobootcamp.sample.parkingLot.ParkingReport;

public class HashTagParkingReportPrinter implements ParkingReportPrinter {
  @Override
  public void print(ParkingReport parkingReport) {
    parkingReport.getLines().forEach(line -> {
          for (int i = 0; i < line.level+1; i++)
            System.out.print("# ");
          System.out.print(line.role + " ");
          System.out.print(line.parkedSpace  + " ");
          System.out.println(line.totalSpace);
        }
    );
  }
}
