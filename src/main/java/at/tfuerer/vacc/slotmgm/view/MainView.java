package at.tfuerer.vacc.slotmgm.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import at.tfuerer.vacc.slotmgm.service.DataStreamManager;
import at.tfuerer.vacc.slotmgm.view.atcpanel.AtcPanel;
import at.tfuerer.vacc.slotmgm.view.atcpanel.AtcPanelController;
import at.tfuerer.vacc.slotmgm.view.data.UpdateProgressPanel;
import at.tfuerer.vacc.slotmgm.view.setup.SetupPanel;
import at.tfuerer.vacc.slotmgm.view.setup.SetupPanelController;
import at.tfuerer.vacc.slotmgm.view.slotview.AircraftTable;
import at.tfuerer.vacc.slotmgm.view.slotview.AircraftViewController;
import at.tfuerer.vacc.slotmgm.view.slotview.SlotTable;
import at.tfuerer.vacc.slotmgm.view.slotview.SlotViewController;

@Route("slot")
@UIScope
public class MainView extends VerticalLayout {
  private static final long serialVersionUID = 1L;

  @Autowired
  private SetupPanelController setupController;

  @Autowired
  private AtcPanelController atcController;

  @Autowired
  private DataStreamManager dataSteamManager;

  @Autowired
  private AircraftViewController aircraftViewController;

  @Autowired
  private SlotViewController slotViewController;

  @PostConstruct
  public void initUI() {
    this.add(new SetupPanel(setupController), new UpdateProgressPanel(dataSteamManager), new AtcPanel(atcController),
        new HorizontalLayout(new SlotTable(slotViewController), new AircraftTable(aircraftViewController)));
  }
}
