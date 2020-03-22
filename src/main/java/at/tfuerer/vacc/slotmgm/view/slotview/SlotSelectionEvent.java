package at.tfuerer.vacc.slotmgm.view.slotview;

import com.vaadin.flow.component.Component;

public class SlotSelectionEvent {

  private final Component source;

  public SlotSelectionEvent(Component source) {
    this.source = source;
  }

  public Component getSource() {
    return source;
  }

}
