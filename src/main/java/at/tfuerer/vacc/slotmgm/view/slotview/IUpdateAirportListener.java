package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.List;

import at.tfuerer.vacc.slotmgm.model.Aircraft;
import at.tfuerer.vacc.slotmgm.model.Airport;

interface IUpdateAirportListener {
  void updateAirport(Airport airport, List<Aircraft> aircrafts);

}
