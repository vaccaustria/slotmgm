package at.tfuerer.vacc.slotmgm.view.data;

import java.util.Optional;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.spring.annotation.UIScope;

import at.tfuerer.vacc.slotmgm.service.DataStreamManager;
import at.tfuerer.vacc.slotmgm.service.IDataUpdateProgressListener;

@UIScope
public class UpdateProgressPanel extends HorizontalLayout implements IDataUpdateProgressListener {
  private static final long serialVersionUID = 1L;
  private final ProgressBar progressBar;

  public UpdateProgressPanel(DataStreamManager dataStreamManager) {
    progressBar = new ProgressBar(0, 100);
    this.add(progressBar);

    dataStreamManager.registerUpdateListener(this);
  }

  @Override
  public void update(int progress, int state) {
    Optional<UI> ui = getUI();
    if (ui.isPresent()) {
      ui.get().access(() -> progressBar.setValue(progress <= 0 ? 0 : progress));
    }
  }

}
