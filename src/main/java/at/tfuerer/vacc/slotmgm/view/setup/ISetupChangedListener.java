package at.tfuerer.vacc.slotmgm.view.setup;

import at.tfuerer.vacc.slotmgm.model.Airport;

public interface ISetupChangedListener {

  void update(Airport selectedAirport);

}
