package com.gigigo.orchextra.domain.model.actions.types;

import com.gigigo.orchextra.domain.interactors.actions.ActionDispatcher;
import com.gigigo.orchextra.domain.model.actions.ActionType;
import com.gigigo.orchextra.domain.model.actions.strategy.BasicAction;
import com.gigigo.orchextra.domain.model.actions.strategy.Notification;
import com.gigigo.orchextra.domain.model.actions.strategy.Schedule;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 15/12/15.
 */
public class EmptyAction extends BasicAction {

  public EmptyAction(String id, String trackId, String url, Notification notification,
      Schedule schedule) {
    super(id, trackId, url, notification, schedule);
    this.actionType = ActionType.NOT_DEFINED;
  }

  public EmptyAction() {
    super("0", "0", "", null, null);
    this.actionType = ActionType.NOT_DEFINED;
  }

  public EmptyAction(String id) {
    super(id, "0", "", null, null);
    this.actionType = ActionType.NOT_DEFINED;
  }

  @Override protected void performSimpleAction(ActionDispatcher actionDispatcher) {
    actionDispatcher.dispatchAction(this);
  }

  @Override protected void performNotifAction(ActionDispatcher actionDispatcher) {
    actionDispatcher.dispatchAction(this, notifFunctionality.getNotification());
  }
}
