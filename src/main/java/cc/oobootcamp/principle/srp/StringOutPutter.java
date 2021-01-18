package cc.oobootcamp.principle.srp;

import java.util.logging.Logger;

public class StringOutPutter {

  private static final Logger LOGGER = Logger.getLogger(StringOutPutter.class.getName());

  public void toConsole(String source, String target) {
    LOGGER.info("toConsole: Source = " + source + "; Target = " + target);
  }

  public void toFile(String source, String target) {
    LOGGER.info("toFile: Source = " + source + "; Target = " + target);
  }
}
