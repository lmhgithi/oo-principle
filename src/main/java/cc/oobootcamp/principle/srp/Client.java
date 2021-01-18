package cc.oobootcamp.principle.srp;

public class Client {

  public static void main(String[] args) {
    String source = "2021/01/18";
    StringConverter stringConverter = new StringConverter();
    StringOutPutter stringOutPutter = new StringOutPutter();
    StringStatistic stringStatistic = new StringStatistic();

    String convertResult = stringConverter.convertSlash(source);
    String target = convertResult == null
        ? stringStatistic.count(source) : convertResult;
    stringOutPutter.toConsole(source, target);
  }
}
