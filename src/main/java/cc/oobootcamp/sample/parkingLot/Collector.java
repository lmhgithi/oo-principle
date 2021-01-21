package cc.oobootcamp.sample.parkingLot;

import java.util.List;

public interface Collector {
  public ParkingReport.ReportLine collect(ParkingLot parkingLot);

  public ParkingReport collect(AbstractParkingPerson abstractParkingPerson);

  public ParkingReport collect(ParkingManager parkingManager);


}
