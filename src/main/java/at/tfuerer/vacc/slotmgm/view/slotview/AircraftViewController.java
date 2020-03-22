package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.tfuerer.vacc.slotmgm.model.Aircraft;
import at.tfuerer.vacc.slotmgm.model.Airport;
import de.zilchinger.vatsimdataapi.VatsimDataManager;

@Service
public class AircraftViewController {
  @Autowired
  private VatsimDataManager dataMgr;

  private List<Aircraft> unassignedAircrafts;

  private final List<IUpdateAircraftListener> listeners = new ArrayList<>();

  @PostConstruct
  private void init() {
    dataMgr.addVatsimDataUpdateListener((pilots, servers, controllers, airports) -> {
      List<Aircraft> aircrafts = pilots.parallelStream()
          .filter(p -> p.getFlightplanInfo().getDepartureAirport() != null)
          .filter(p -> p.getFlightplanInfo().getDepartureAirport().getIcao().equalsIgnoreCase("LOWW"))
          .map(p -> new Aircraft(p.getCallsign())).collect(Collectors.toList());

      // TODO need to be updated be the currently selected airport
      Airport airport = new Airport("LOWW");
      listeners.parallelStream().forEach(l -> l.update(aircrafts));
    });
  }

  public void assignAircraft(Aircraft aircraft) {
    unassignedAircrafts.remove(aircraft);
  }

  void register(IUpdateAircraftListener listener) {
    listeners.add(listener);
  }

}
