package at.tfuerer.vacc.slotmgm.view.setup;

import java.util.List;

import at.tfuerer.vacc.slotmgm.model.Airport;

public interface IAirPortDataChangedListener {
  void update(List<Airport> airports);

}
