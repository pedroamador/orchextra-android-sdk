/*
 * Created by Orchextra
 *
 * Copyright (C) 2016 Gigigo Mobile Services SL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigigo.orchextra.control.controllers.action.scheduler;

import com.gigigo.orchextra.domain.abstractions.actions.ActionsScheduler;
import com.gigigo.orchextra.domain.abstractions.actions.ActionsSchedulerController;
import com.gigigo.orchextra.domain.abstractions.actions.ActionsSchedulerPersistor;
import com.gigigo.orchextra.domain.model.actions.ScheduledAction;
import com.gigigo.orchextra.domain.model.actions.strategy.BasicAction;
import java.util.List;
//TODO LIB_CRUNCH orchextrasdk-domain
public class ActionsSchedulerControllerImpl implements ActionsSchedulerController {

  //TODO LIB_CRUNCH orchextrasdk-domain
  private final ActionsScheduler actionsScheduler;
  //TODO LIB_CRUNCH orchextrasdk-domain
  private final ActionsSchedulerPersistor actionsSchedulerPersistor;

  //TODO LIB_CRUNCH orchextrasdk-domain
  public ActionsSchedulerControllerImpl(ActionsScheduler actionsScheduler,
      ActionsSchedulerPersistor actionsSchedulerPersistor) {

    this.actionsScheduler = actionsScheduler;
    this.actionsSchedulerPersistor = actionsSchedulerPersistor;
  }

  @Override public void cancelPendingActionWithId(String id, boolean forceCancel) {
    //TODO LIB_CRUNCH orchextrasdk-domain
    ScheduledAction action = actionsSchedulerPersistor.getScheduledActionWithId(id);

    if (action == null) {
      action = BasicAction.generateCancelActionHolder(id, forceCancel);
    }

    cancelAction(forceCancel, action);
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  @Override public void addAction(ScheduledAction action) {
    actionsSchedulerPersistor.addAction(action);
    actionsScheduler.scheduleAction(action);
  }

  @Override public void scheduleAllPendingActions() {
    //TODO LIB_CRUNCH orchextrasdk-domain
    List<ScheduledAction> scheduledActions = actionsSchedulerPersistor.obtainAllPendingActions();
    for (ScheduledAction scheduledAction : scheduledActions) {
      actionsScheduler.scheduleAction(scheduledAction);
    }
  }

  @Override public void cancelAllPendingActions(boolean forceCancel) {
    //TODO LIB_CRUNCH orchextrasdk-domain
    List<ScheduledAction> scheduledActions = actionsSchedulerPersistor.obtainAllPendingActions();
    for (ScheduledAction scheduledAction : scheduledActions) {
      if (scheduledAction.isCancelable() || forceCancel) {
        cancelAction(forceCancel, scheduledAction);
      }
    }
  }

  @Override public void removeScheduledActionWithId(String id) {
    //TODO LIB_CRUNCH orchextrasdk-domain
    ScheduledAction action = actionsSchedulerPersistor.getScheduledActionWithId(id);
    removeAction(action);
  }

  @Override public void removeScheduledAction(ScheduledAction scheduledAction) {
    //TODO LIB_CRUNCH orchextrasdk-domain
    removeAction(scheduledAction);
  }

  private void cancelAction(boolean forceCancel, ScheduledAction action) {
    //TODO LIB_CRUNCH orchextrasdk-domain
    if (action.isCancelable() || forceCancel) {
      cancelScheduledAction(action);
      removeScheduledAction(action);
    }
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  private void cancelScheduledAction(ScheduledAction action) {
    actionsScheduler.cancelAction(action);
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  private void removeAction(ScheduledAction action) {
    actionsSchedulerPersistor.removeAction(action);
  }
}
