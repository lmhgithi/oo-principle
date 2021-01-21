package cc.oobootcamp.sample.parkingLot.clients;

import cc.oobootcamp.sample.parkingLot.AbstractParkingPerson;
import cc.oobootcamp.sample.parkingLot.Car;
import cc.oobootcamp.sample.parkingLot.GraduateParkingBoy;
import cc.oobootcamp.sample.parkingLot.NormalParkingLot;
import cc.oobootcamp.sample.parkingLot.ParkingDirector;
import cc.oobootcamp.sample.parkingLot.ParkingLot;
import cc.oobootcamp.sample.parkingLot.ParkingManager;
import cc.oobootcamp.sample.parkingLot.SmartParkingBoy;
import cc.oobootcamp.sample.parkingLot.SuperParkingBoy;
import cc.oobootcamp.sample.parkingLot.Ticket;
import cc.oobootcamp.sample.parkingLot.util.DefaultParkingReportPrinter;
import cc.oobootcamp.sample.parkingLot.util.HashTagParkingReportPrinter;
import cc.oobootcamp.sample.parkingLot.util.ParkingReportPrinter;
import java.util.ArrayList;
import java.util.List;

public class ParkingDirectorClient {


  public static final int spacePerParkingLot = 10;

  public static void main(String[] args) {
    ParkingReportPrinter hashTagParkingReportPrinter = new HashTagParkingReportPrinter();
    ParkingManager parkingManager = new ParkingManager(new ArrayList<AbstractParkingPerson>() {{
      add(new GraduateParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
      add(new SmartParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
      add(new SuperParkingBoy(new ArrayList<ParkingLot>() {{
        add(new NormalParkingLot(spacePerParkingLot));
        add(new NormalParkingLot(spacePerParkingLot));
      }}));
    }}, new ArrayList<ParkingLot>() {{
      add(new NormalParkingLot(spacePerParkingLot));
      add(new NormalParkingLot(spacePerParkingLot));
    }});
    ParkingDirector.getParkingReport(parkingManager);
    System.out.println("==============================================");
    ParkingDirector.getParkingReport(parkingManager, hashTagParkingReportPrinter);

  }
}