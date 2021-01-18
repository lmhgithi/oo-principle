package cc.oobootcamp.principle.srp;

public class StringConverter {

  public String convertHorizontalBar(String source) {
    String target;
    if (source.matches("\\d{4}-\\d{2}-\\d{2}")) {
      target = source.replaceAll("-", "");
      return target;
    }

    return null;
  }

  public String convertSlash(String source) {
    String target;
    if (source.matches("\\d{4}/\\d{2}/\\d{2}")) {
      target = source.replaceAll("/", "");
      return target;
    }

    return null;
  }
}
