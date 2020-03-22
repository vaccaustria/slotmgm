package at.tfuerer.vacc.slotmgm.view.atcpanel;

import java.util.List;

import at.tfuerer.vacc.slotmgm.model.ATC;

interface IUpdateATCListener {
  void update(List<ATC> onlineATC);
}
