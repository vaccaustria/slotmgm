package at.tfuerer.vacc.slotmgm.model;

import java.time.LocalTime;

public class Slot {
  private long id;
  private LocalTime start;
  private LocalTime end;

  private Aircraft assigned;

  public Slot(LocalTime start, LocalTime end) {
    this.start = start;
    this.end = end;
  }

  public Slot(LocalTime start, LocalTime end, Aircraft aircraft) {
    this(start, end);
    assigned = aircraft;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalTime getStart() {
    return start;
  }

  public void setStart(LocalTime start) {
    this.start = start;
  }

  public LocalTime getEnd() {
    return end;
  }

  public void setEnd(LocalTime end) {
    this.end = end;
  }

  public Aircraft getAssigned() {
    return assigned;
  }

  public void setAssigned(Aircraft assigned) {
    this.assigned = assigned;
  }

}
