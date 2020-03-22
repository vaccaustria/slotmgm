package at.tfuerer.vacc.slotmgm.view.atcpanel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import at.tfuerer.vacc.slotmgm.model.ATC;
import at.tfuerer.vacc.slotmgm.model.Airport;
import at.tfuerer.vacc.slotmgm.view.setup.SetupChangedEvent;
import de.zilchinger.vatsimdataapi.VatsimDataManager;
import de.zilchinger.vatsimdataapi.model.VatsimController;

@Service
public class AtcPanelController implements ApplicationListener<SetupChangedEvent> {

  @Autowired
  private VatsimDataManager dataMgr;

  private Airport selectedAirport;

  private final List<IUpdateATCListener> listeners = new ArrayList<>();

  @PostConstruct
  private void init() {
    dataMgr.addVatsimDataUpdateListener((pilots, servers, controllers, airports) -> {
      List<ATC> atc = filterControllers(controllers, selectedAirport);
      listeners.parallelStream().forEach(l -> l.update(atc));
    });
  }

  private List<ATC> filterControllers(ArrayList<VatsimController> controllers, Airport airport) {
    return controllers.stream()
        .filter(c -> c.getCallsign().toUpperCase().startsWith(airport != null ? airport.getIcao() : "LOWW"))
        .map(c -> new ATC(c.getCallsign(), c.getFrequency())).collect(Collectors.toList());
  }

  @Scheduled(fixedRate = 1000L * 60 * 5)
  void updateOnlineAtc() {
  }

  void registerForUpdate(IUpdateATCListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void onApplicationEvent(SetupChangedEvent event) {
    selectedAirport = event.getAirport();
  }

}
