package com.gigigo.orchextra.control.controllers.config;

import com.gigigo.orchextra.control.InteractorResult;
import com.gigigo.orchextra.control.controllers.base.Controller;
import com.gigigo.orchextra.control.invoker.InteractorExecution;
import com.gigigo.orchextra.control.invoker.InteractorInvoker;
import com.gigigo.orchextra.domain.interactors.base.InteractorError;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraUpdates;

import javax.inject.Provider;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class ConfigController extends Controller<ConfigDelegate> {

    private final InteractorInvoker interactorInvoker;
    private final Provider<InteractorExecution> sendConfigInteractorExecution;
    private final ConfigObservable configObservable;

    public ConfigController(ThreadSpec mainThreadSpec, InteractorInvoker interactorInvoker,
                            Provider<InteractorExecution> sendConfigInteractorExecution,
                            ConfigObservable configObservable) {

        super(mainThreadSpec);

        this.interactorInvoker = interactorInvoker;

        this.sendConfigInteractorExecution = sendConfigInteractorExecution;
        this.configObservable = configObservable;
    }

    @Override
    public void onDelegateAttached() {

    }

    public void sendConfiguration() {
        sendConfigInteractorExecution.get().result(new InteractorResult<OrchextraUpdates>() {
            @Override public void onResult(OrchextraUpdates result) {
                if (result != null) {
                    notifyChanges(result);
                }
                getDelegate().configSuccessful();
            }
        }).error(InteractorError.class, new InteractorResult<InteractorError>() {
            @Override public void onResult(InteractorError result) {
                    manageInteractorError(result);
            }
        }).execute(interactorInvoker);
    }

    private void manageInteractorError(InteractorError result) {
        getDelegate().configError(result.getError());
    }

    private void notifyChanges(OrchextraUpdates result) {
        if (result.hasChanges()){
            configObservable.notifyObservers(result);
        }
    }
}