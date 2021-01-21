package cc.oobootcamp.sample.parkingLot.util;

import cc.oobootcamp.sample.parkingLot.ParkingManager;
import cc.oobootcamp.sample.parkingLot.ParkingReport;

public class NormalReportGenerator implements ReportGenerator {

  private ParkingReport parkingReport = new ParkingReport();

  public static final int FIRST_LEVEL = 0;
  public static final int SECOND_LEVEL = 1;
  public static final int THIRD_LEVEL = 2;

  public static final String MANAGER = "M";
  public static final String PARKING_BOY = "B";
  public static final String PARKING_LOT = "P";

  public ParkingReport generate(ParkingManager parkingManager) {
    parkingReport = new ParkingReport();
    addToReportLine(FIRST_LEVEL, MANAGER, parkingManager.getTotalSpaceOfAll(), parkingManager.getAvailableSpaceOfAll());
    addToReportLine(SECOND_LEVEL, PARKING_LOT, parkingManager.getTotalSpace(), parkingManager.getAvailableSpace());

    parkingManager.getParkingBoys()
        .forEach(parkingBoy -> {
              addToReportLine(SECOND_LEVEL, PARKING_BOY, parkingBoy.getTotalSpace(), parkingBoy.getAvailableSpace());
              parkingBoy.getParkingLots()
                  .forEach(parkingLot ->
                      addToReportLine(THIRD_LEVEL, PARKING_LOT, parkingLot.getTotalSpace(), parkingLot.getAvailableSpace()));
            }
        );

    return parkingReport;
  }

  private void addToReportLine(int level, String role, int totalSpace, int availableSpace) {
    parkingReport.getLines().add(new ParkingReport.ReportLine(level, role,
        totalSpace - availableSpace,
        totalSpace));
  }
}
