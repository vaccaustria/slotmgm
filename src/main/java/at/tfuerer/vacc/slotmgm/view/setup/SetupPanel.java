package at.tfuerer.vacc.slotmgm.view.setup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

import at.tfuerer.vacc.slotmgm.model.Airport;

@UIScope
public class SetupPanel extends HorizontalLayout implements IAirPortDataChangedListener {
  private static final long serialVersionUID = 1L;
  private final ComboBox<Airport> cbAirports;
  private final SetupPanelController controller;

  public SetupPanel(SetupPanelController controller) {
    this.controller = controller;
    cbAirports = new ComboBox<>("Choose an airport:");
    cbAirports.setItems(Arrays.asList(new Airport("LOWW")));
    cbAirports.setItemLabelGenerator(i -> i.getIcao());
    cbAirports.addValueChangeListener(e -> controller.changeAirport(e.getValue()));

    ComboBox<Integer> cbSlotTimeSpan = new ComboBox<>("Choose the timespan between slots in seconds:");
    cbSlotTimeSpan.setItems(IntStream.iterate(60, f -> f + 10).limit(3 * 60).boxed());
    cbSlotTimeSpan.addValueChangeListener(e -> controller.setSlotPeriode(e.getValue()));

    this.add(cbAirports, cbSlotTimeSpan);

    controller.registerListener(this);
  }

  @Override
  public void update(List<Airport> airports) {
    Optional<UI> ui = getUI();
    if (ui.isPresent()) {
      ui.get().access(() -> {
        Airport selection = cbAirports.getValue();
        cbAirports.setItems(airports);
        cbAirports.setValue(selection);
      });
    }
  }

}
