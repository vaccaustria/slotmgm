package at.tfuerer.vacc.slotmgm.model;

public class Aircraft {
  private final String callsign;
  private AircraftStatus status;
  private String origin;
  private String destination;

  public Aircraft(String callsign) {
    this.callsign = callsign;
    this.status = AircraftStatus.UNCLEAR;
  }

  public String getCallsign() {
    return callsign;
  }

  public AircraftStatus getStatus() {
    return status;
  }

  public void setStatus(AircraftStatus status) {
    this.status = status;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

}
