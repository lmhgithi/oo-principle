package cc.oobootcamp.sample.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingReportCollector implements Collector {
  @Override
  public ParkingReport.ReportLine collect(ParkingLot parkingLot) {
    return getReportLine(2, parkingLot.getNameTag(), parkingLot.getTotalSpace(), parkingLot.getAvailableSpace());
  }

  @Override
  public ParkingReport collect(AbstractParkingPerson abstractParkingPerson) {
    List<ParkingReport.ReportLine> reportLines = new ArrayList<>();
    reportLines.add(getReportLine(1, abstractParkingPerson.getNameTag(), abstractParkingPerson.getTotalSpace(),
        abstractParkingPerson.getAvailableSpace()));

    abstractParkingPerson.getParkingLots().forEach(parkingLot ->
        reportLines.add(parkingLot.getStatistics(this))
    );
    return new ParkingReport(reportLines);
  }

  @Override
  public ParkingReport collect(ParkingManager parkingManager) {
    List<ParkingReport.ReportLine> reportLines = new ArrayList<>();

    reportLines.add(getReportLine(0, parkingManager.getNameTag(), parkingManager.getTotalSpaceOfAll(),
        parkingManager.getAvailableSpaceOfAll()));
    reportLines.add(
        getReportLine(1, parkingManager.getParkingLots().get(0).getNameTag(), parkingManager.getTotalSpace(),
            parkingManager.getAvailableSpace()));

    parkingManager.getParkingBoys().forEach(parkingBoy -> {
      reportLines.addAll(parkingBoy.getStatistics(this).getLines());
    });
    return new ParkingReport(reportLines);
  }

  private ParkingReport.ReportLine getReportLine(int level, String nameTag, int totalSpace, int availableSpace) {
    return new ParkingReport.ReportLine(level,
        nameTag,
        totalSpace - availableSpace,
        totalSpace);
  }
}
