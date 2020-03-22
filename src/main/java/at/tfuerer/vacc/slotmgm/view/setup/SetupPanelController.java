package at.tfuerer.vacc.slotmgm.view.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import at.tfuerer.vacc.slotmgm.model.Airport;
import de.zilchinger.vatsimdataapi.VatsimDataManager;

@Service
public class SetupPanelController {

  @Autowired
  private VatsimDataManager dataMgr;

  @Autowired
  private ApplicationEventPublisher eventBroker;

  private Airport selectedAirport;

  private Integer slotPeriode;

  private final List<IAirPortDataChangedListener> listeners = new ArrayList<>();

  @PostConstruct
  private void init() {
    dataMgr.addVatsimDataUpdateListener((pilots, servers, controllers, airports) -> {
      List<Airport> filteredAirports = airports.stream().filter(a -> a.getIcao().toUpperCase().startsWith("LO"))
          .map(va -> new Airport(va.getIcao())).collect(Collectors.toList());
      listeners.parallelStream().forEach(l -> l.update(filteredAirports));
    });
  }

  public void changeAirport(Airport airport) {
    this.selectedAirport = airport;
    eventBroker.publishEvent(new SetupChangedEvent(this, airport));
  }

  public Airport getSelectedAirport() {
    return selectedAirport;
  }

  public void setSlotPeriode(Integer slotPeriode) {
    this.slotPeriode = slotPeriode;
  }

  public Integer getSlotPeriode() {
    return slotPeriode;
  }

  public void registerListener(IAirPortDataChangedListener listener) {
    listeners.add(listener);
  }

}
