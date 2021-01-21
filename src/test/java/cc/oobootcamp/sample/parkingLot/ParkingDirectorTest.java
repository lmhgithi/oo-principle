package cc.oobootcamp.sample.parkingLot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingDirectorTest {

  private ParkingManager parkingManager;

  private final int spacePerParkingLot = 10;

  @BeforeEach
  void beforeEach() {
    parkingManager = new ParkingManager(new ArrayList<AbstractParkingPerson>() {{
      add(new GraduateParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
    }}, new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(spacePerParkingLot));
    }});
  }

  @Test
  void should_get_report() {
    ParkingReport parkingReportExpected = new ParkingReport(new ArrayList<ParkingReport.ReportLine>() {{
      add(new ParkingReport.ReportLine(0, "M", 3, 30));
      add(new ParkingReport.ReportLine(1, "P", 0, 10));
      add(new ParkingReport.ReportLine(1, "B", 3, 20));
      add(new ParkingReport.ReportLine(2, "P", 1, 10));
      add(new ParkingReport.ReportLine(2, "P", 2, 10));
    }});
    parkingManager.getParkingBoys().get(0).getParkingLots().get(0).setAvailableSpace(9);
    parkingManager.getParkingBoys().get(0).getParkingLots().get(1).setAvailableSpace(8);

    ParkingReport parkingReport = ParkingDirector.generateReport(parkingManager);

    assertThat(parkingReport).isNotNull();
    assertThat(parkingReport.getLines().get(0).level).isEqualTo(parkingReportExpected.getLines().get(0).level);
    assertThat(parkingReport.getLines().get(0).parkedSpace).isEqualTo(parkingReportExpected.getLines().get(0).parkedSpace);
    assertThat(parkingReport.getLines().get(0).role).isEqualTo(parkingReportExpected.getLines().get(0).role);
    assertThat(parkingReport.getLines().get(0).totalSpace).isEqualTo(parkingReportExpected.getLines().get(0).totalSpace);
    assertThat(parkingReport.getLines().get(1).parkedSpace).isEqualTo(parkingReportExpected.getLines().get(1).parkedSpace);
    assertThat(parkingReport.getLines().get(2).parkedSpace).isEqualTo(parkingReportExpected.getLines().get(2).parkedSpace);
    assertThat(parkingReport.getLines().get(3).parkedSpace).isEqualTo(parkingReportExpected.getLines().get(3).parkedSpace);
    assertThat(parkingReport.getLines().get(4).parkedSpace).isEqualTo(parkingReportExpected.getLines().get(4).parkedSpace);
  }

}