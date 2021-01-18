package cc.oobootcamp.principle.isp;

import java.util.logging.Logger;

public class AdultClient {
    private static final Logger LOGGER = Logger.getLogger(AdultClient.class.getName());

    public static void main(String[] args) {
        Adult adult = new Adult();
        adult.work();
        adult.work();
        adult.work();
        adult.work();
        adult.work();
        adult.report();
        adult.report();
        adult.report();
        adult.report();
        adult.report();
        adult.report();
        adult.eat();
        adult.drink();
        adult.snore();

        long workTimes = adult.getTimes("WORK");
        long reportTimes = adult.getTimes("REPORT");
        long eatTimes = adult.getTimes("EAT");
        long drinkTimes = adult.getTimes("DRINK");
        long snoreTimes = adult.getTimes("SNORE");

        LOGGER.info("Work " + workTimes + " times");
        LOGGER.info("Report " + reportTimes + " times");
        LOGGER.info("Eat " + eatTimes + " times");
        LOGGER.info("Drink " + drinkTimes + " times");
        LOGGER.info("Snore " + snoreTimes + " times");
    }
}
