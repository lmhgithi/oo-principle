package cc.oobootcamp.principle.dip;

import java.util.List;

public interface Printer {
  public void print(List<Item> items, double totalPrice);
}
