package cc.oobootcamp.sample.parkingLot.util;

import cc.oobootcamp.sample.parkingLot.ParkingManager;
import cc.oobootcamp.sample.parkingLot.ParkingReport;

public interface ReportGenerator {
  public ParkingReport generate(ParkingManager parkingManager);
}
