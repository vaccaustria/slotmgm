package at.tfuerer.vacc.slotmgm.view.slotview;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.tfuerer.vacc.slotmgm.model.Aircraft;
import at.tfuerer.vacc.slotmgm.model.Airport;
import at.tfuerer.vacc.slotmgm.model.Slot;
import de.zilchinger.vatsimdataapi.VatsimDataManager;

@Service
public class SlotViewController {
  private final List<Slot> slots = new ArrayList<>();
  private final List<ISlotUpdateListener> listeners = new ArrayList<>();

  @Autowired
  private VatsimDataManager dataMgr;

  @PostConstruct
  private void init() {
    dataMgr.addVatsimDataUpdateListener((pilots, servers, controllers, airports) -> {
      List<Aircraft> aircrafts = pilots.parallelStream()
          .filter(p -> p.getFlightplanInfo().getDepartureAirport() != null)
          .filter(p -> p.getFlightplanInfo().getDepartureAirport().getIcao().equalsIgnoreCase("LOWW"))
          .map(p -> new Aircraft(p.getCallsign())).collect(Collectors.toList());

      // TODO need to be updated be the currently selected airport
      Airport airport = new Airport("LOWW");
      listeners.parallelStream().forEach(l -> l.update(slots));
    });
  }

  private List<Slot> generateSlots(LocalTime start, LocalTime end, int period) {
    List<Slot> slots = new ArrayList<>();

    LocalTime temp = start;
    while (end.minusSeconds(period + 1).isAfter(temp)) {
      slots.add(new Slot(temp, temp.plusSeconds(period), null));
      temp = temp.plusSeconds(period);
    }

    return slots;
  }

  public void register(ISlotUpdateListener listener) {
    this.listeners.add(listener);
  }

}
