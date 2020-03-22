package at.tfuerer.vacc.slotmgm.model;

public class ATC {
  private final String callsign;
  private final String frequency;
  private ATCStatus status;

  public ATC(String callsign, String frequency) {
    super();
    this.callsign = callsign;
    this.frequency = frequency;
    this.status = ATCStatus.FREE;
  }

  public String getCallsign() {
    return callsign;
  }

  public String getFrequency() {
    return frequency;
  }

  public ATCStatus getStatus() {
    return status;
  }

  public void setStatus(ATCStatus status) {
    this.status = status;
  }

}
