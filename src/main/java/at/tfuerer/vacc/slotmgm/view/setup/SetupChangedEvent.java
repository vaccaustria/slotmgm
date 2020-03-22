package at.tfuerer.vacc.slotmgm.view.setup;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationEvent;

import at.tfuerer.vacc.slotmgm.model.Airport;

public class SetupChangedEvent extends ApplicationEvent {
  private static final long serialVersionUID = 1L;
  private final Airport airport;
  private final List<Airport> filteredAirports;
  private Integer slotspan;

  public SetupChangedEvent(Object source, Airport airport) {
    this(source, airport, Collections.emptyList());
  }

  public SetupChangedEvent(Object setupPanelController, Airport selectedAirport, List<Airport> filteredAirports) {
    super(setupPanelController);
    airport = selectedAirport;
    this.filteredAirports = filteredAirports;
  }

  public Airport getAirport() {
    return airport;
  }

  public Integer getSlotspan() {
    return slotspan;
  }

  public void setSlotspan(Integer slotspan) {
    this.slotspan = slotspan;
  }

  public List<Airport> getFilteredAirports() {
    return filteredAirports;
  }
}
