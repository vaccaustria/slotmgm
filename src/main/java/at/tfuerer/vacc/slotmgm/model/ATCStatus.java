package at.tfuerer.vacc.slotmgm.model;

public enum ATCStatus {
  FREE("lightblue"), BUSY("orange"), OVERLOAD("red");

  private final String color;

  private ATCStatus(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }
}
