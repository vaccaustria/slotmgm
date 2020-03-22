package at.tfuerer.vacc.slotmgm.model;

public class Airport {
  private String icao;

  public Airport() {
  }

  public Airport(String icao) {
    this.icao = icao;
  }

  public String getIcao() {
    return icao;
  }

  public void setIcao(String icao) {
    this.icao = icao;
  }

}
