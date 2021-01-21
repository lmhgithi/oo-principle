package cc.oobootcamp.sample.parkingLot;

/**
 * 给一个parking director,
 * 当director查看报表，
 * 报表显示parking manager管理的停车场，以及manager手下的parking boy，及parking boy所管理的停车场
 */
public class ParkingDirector {
  public static ParkingReport parkingReport = new ParkingReport();

  public static final int FIRST_LEVEL = 0;
  public static final int SECOND_LEVEL = 1;
  public static final int THIRD_LEVEL = 2;

  public static final String MANAGER = "M";
  public static final String PARKING_BOY = "B";
  public static final String PARKING_LOT = "P";

  public static ParkingReport generateReport(ParkingManager parkingManager) {
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

  private static void addToReportLine(int level, String role, int totalSpace, int availableSpace) {
    parkingReport.getLines().add(new ParkingReport.ReportLine(level, role,
        totalSpace - availableSpace,
        totalSpace));
  }
}
