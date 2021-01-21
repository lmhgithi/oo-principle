package cc.oobootcamp.sample.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingReport {

  private final List<ReportLine> lines;

  public ParkingReport(List<ReportLine> lines) {
    this.lines = lines;
  }

  public ParkingReport() {
    this.lines = new ArrayList<>();
  }

  public List<ReportLine> getLines() {
    return lines;
  }

  public static class ReportLine {
    public int level;
    public String role;
    public int parkedSpace;
    public int totalSpace;

    public ReportLine(int level, String role, int parkedSpace, int totalSpace) {
      this.level = level;
      this.role = role;
      this.parkedSpace = parkedSpace;
      this.totalSpace = totalSpace;
    }
  }
}

