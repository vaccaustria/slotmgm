package at.tfuerer.vacc.slotmgm.view.atcpanel;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import at.tfuerer.vacc.slotmgm.model.ATC;

public class AtcPanel extends HorizontalLayout implements IUpdateATCListener {
  private static final long serialVersionUID = 1L;
  private final AtcPanelController controller;

  public AtcPanel(AtcPanelController controller) {
    this.controller = controller;
    controller.registerForUpdate(this);
  }

  private com.vaadin.flow.component.Component layoutATCBox(ATC a) {
    VerticalLayout layout = new VerticalLayout(new Label(a.getCallsign()), new Label(a.getFrequency() + "MHz"));
    layout.getStyle().set("background-color", a.getStatus().getColor());
    return layout;
  }

  @Override
  public void update(List<ATC> onlineATC) {
    Optional<UI> optional = getUI();
    if (optional.isPresent()) {
      optional.get().access(() -> {
        this.removeAll();
        onlineATC.forEach(a -> this.add(layoutATCBox(a)));
      });
    }
  }

}
