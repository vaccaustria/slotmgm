package at.tfuerer.vacc.slotmgm.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.zilchinger.vatsimdataapi.VatsimDataManager;
import de.zilchinger.vatsimdataapi.exception.VatsimDataManagerException;

@Service
public class DataStreamManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataStreamManager.class);
  @Autowired
  private VatsimDataManager dataMgr;

  private final List<IDataUpdateProgressListener> listeners = new ArrayList<>();

  @Scheduled(fixedRate = 1 * 30 * 1000)
  private void updateDataStream() {
    try {
      dataMgr.update((progress, state) -> listeners.parallelStream().forEach(l -> l.update(progress, state)));
      LOGGER.info("Data update requested.");
    } catch (VatsimDataManagerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void registerUpdateListener(IDataUpdateProgressListener listener) {
    listeners.add(listener);
  }
}
