package at.tfuerer.vacc.slotmgm.view.slotview;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import at.tfuerer.vacc.slotmgm.model.Slot;

public class SlotTable extends Div implements ISlotUpdateListener {
  private static final long serialVersionUID = 1L;

  private final Grid<Slot> grid;

  public SlotTable(SlotViewController controller) {
    this.setClassName("slotTable");
    grid = new Grid<Slot>(Slot.class);
    grid.recalculateColumnWidths();

    this.add(new Label("assigned aircrafts"), grid);
    controller.register(this);
  }

  public void addData(List<Slot> slots) {
    grid.setItems(slots);
    grid.recalculateColumnWidths();
  }

  @Override
  public void update(List<Slot> slots) {
    Optional<UI> ui = this.getUI();
    if (ui.isPresent()) {
      ui.get().access(() -> this.addData(slots));
    }
  }
}