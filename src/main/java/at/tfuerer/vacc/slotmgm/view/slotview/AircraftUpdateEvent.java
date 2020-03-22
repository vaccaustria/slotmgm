package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import at.tfuerer.vacc.slotmgm.model.Aircraft;
import at.tfuerer.vacc.slotmgm.model.Airport;

public class AircraftUpdateEvent extends ApplicationEvent {
  private static final long serialVersionUID = 1L;

  private final Airport airport;
  private final List<Aircraft> aircrafts;

  public AircraftUpdateEvent(Object source, Airport airport, List<Aircraft> aircrafts) {
    super(source);
    this.airport = airport;
    this.aircrafts = aircrafts;
  }

  public Airport getAirport() {
    return airport;
  }

  public List<Aircraft> getAircrafts() {
    return aircrafts;
  }

}
