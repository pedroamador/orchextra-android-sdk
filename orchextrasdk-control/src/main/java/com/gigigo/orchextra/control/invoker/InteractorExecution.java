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

package com.gigigo.orchextra.control.invoker;

import com.gigigo.orchextra.control.InteractorResult;
import com.gigigo.orchextra.domain.interactors.base.Interactor;
import com.gigigo.orchextra.domain.interactors.base.InteractorError;
import com.gigigo.orchextra.domain.interactors.base.InteractorResponse;
import java.util.HashMap;
import java.util.Map;

public class InteractorExecution<T> {
  private InteractorResult<T> interactorResult;
  //TODO LIB_CRUNCH orchextrasdk-domain
  private final Map<Class<? extends InteractorError>, InteractorResult<? extends InteractorError>>
      errors = new HashMap<>(0);
  //TODO LIB_CRUNCH orchextrasdk-domain
  private Interactor<InteractorResponse<T>> interactor;
  private int priority;
  //TODO LIB_CRUNCH orchextrasdk-domain
  public InteractorExecution(Interactor<InteractorResponse<T>> interactor) {
    this.interactor = interactor;
  }

  public InteractorExecution() {
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  public void setInteractor(Interactor<InteractorResponse<T>> interactor) {
    this.interactor = interactor;
  }

  public InteractorExecution<T> result(InteractorResult<T> interactorResult) {
    this.interactorResult = interactorResult;
    return this;
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  public InteractorExecution<T> error(Class<? extends InteractorError> errorClass,
      InteractorResult<? extends InteractorError> interactorError) {
    this.errors.put(errorClass, interactorError);
    return this;
  }

  public InteractorExecution<T> priority(int priority) {
    this.priority = priority;
    return this;
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  public Interactor<InteractorResponse<T>> getInteractor() {
    return interactor;
  }
  //TODO LIB_CRUNCH orchextrasdk-domain
  public InteractorResult<? extends InteractorError> getInteractorErrorResult(
      Class<? extends InteractorError> errorClass) {
    return errors.get(errorClass);
  }

  public InteractorResult<T> getInteractorResult() {
    return interactorResult;
  }

  public void execute(InteractorInvoker interactorInvoker) {
    interactorInvoker.execute(this);
  }

  public int getPriority() {
    return priority;
  }
}
