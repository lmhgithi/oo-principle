package cc.oobootcamp.principle.srp;

import java.util.HashMap;
import java.util.Map;

public class StringStatistic {

  public String count(String source) {
    Map<String, Integer> result = new HashMap<>();
    char[] chars = source.toCharArray();
    for (char c : chars) {
      if (result.get(c) != null) {
        result.put(String.valueOf(c), result.get(c) + 1);
      } else {
        result.put(String.valueOf(c), 1);
      }
    }
    return result.toString();
  }
}
