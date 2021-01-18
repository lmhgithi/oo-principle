package cc.oobootcamp.principle.dip;

import java.util.ArrayList;
import java.util.List;

public class OrderReceiptHandler {
  private final List<Item> items = new ArrayList<>();

  private final PrinterType printerType;

  private final Printer printer;


  public OrderReceiptHandler(List<Item> items, PrinterType printerType) {
    this.printerType = printerType;
    this.items.addAll(items);

    if (printerType.equals(PrinterType.PLAINT)) {
      this.printer = new PlaintPrinter();
    } else if (printerType.equals(PrinterType.XML)) {
      this.printer = new XMLPrinter();
    } else {
      this.printer = new HTMLPrinter();
    }
  }

  public void printReceipt() {
    if (printerType == PrinterType.PLAINT) {
      printer.print(items, calculateTotalPrice());
    }
  }

  private double calculateTotalPrice() {
    double result = 0;
    for (Item item : items) {
      result = result + item.getPrice();
    }
    return result;
  }
}
