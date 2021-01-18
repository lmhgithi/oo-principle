package cc.oobootcamp.principle.lsp;

public class Square implements Quadrilateral {
  private double side;

  public double getSide() {
    return side;
  }

  public void setSide(double side) {
    this.side = side;
  }

  @Override
  public double calculateArea() {
    return side * side;
  }

  @Override
  public double getWidth() {
    return side;
  }

  @Override
  public double getHeight() {
    return side;
  }


}
