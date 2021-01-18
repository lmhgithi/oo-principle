package cc.oobootcamp.principle.lsp;

public class Rectangle implements Quadrilateral {
  protected double width;
  protected double height;

  public void setWidth(double width) {
    this.width = width;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public double calculateArea() {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }
    return width * height;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }
}
