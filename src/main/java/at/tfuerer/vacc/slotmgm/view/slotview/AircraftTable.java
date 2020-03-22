package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import at.tfuerer.vacc.slotmgm.model.Aircraft;

public class AircraftTable extends Div implements IUpdateAircraftListener {
  private static final long serialVersionUID = 1L;
  private final Grid<Aircraft> grid;

  public AircraftTable(AircraftViewController aircraftViewController) {
    grid = new Grid<Aircraft>(Aircraft.class);
    grid.removeColumnByKey("destination");
    grid.removeColumnByKey("origin");
    grid.removeColumnByKey("status");

    this.add(new Label("unmanaged aircrafts/pilots"), grid);
    aircraftViewController.register(this);
  }

  public void addData(List<Aircraft> aircrafts) {
    this.grid.setItems(aircrafts);
  }

  @Override
  public void update(List<Aircraft> aircrafts) {
    Optional<UI> ui = getUI();
    if (ui.isPresent()) {
      ui.get().access(() -> this.addData(aircrafts));
    }
  }

}
