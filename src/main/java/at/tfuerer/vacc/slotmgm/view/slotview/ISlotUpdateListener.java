package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.List;

import at.tfuerer.vacc.slotmgm.model.Slot;

public interface ISlotUpdateListener {
  void update(List<Slot> slots);
}
