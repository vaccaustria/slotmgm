package at.tfuerer.vacc.slotmgm.service;

import org.springframework.context.ApplicationEvent;

public class UpdateProgressEvent extends ApplicationEvent {
  private static final long serialVersionUID = 1L;
  private final int progress;
  private final int state;

  public UpdateProgressEvent(Object source, int progress, int state) {
    super(source);
    this.progress = progress;
    this.state = state;
  }

  public int getProgress() {
    return progress;
  }

  public int getState() {
    return state;
  }
}
