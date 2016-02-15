package com.gigigo.orchextra.di.modules.control;

import com.gigigo.orchextra.control.controllers.authentication.AuthenticationController;
import com.gigigo.orchextra.control.controllers.config.ConfigController;
import com.gigigo.orchextra.control.controllers.config.ConfigObservable;
import com.gigigo.orchextra.control.controllers.proximity.geofence.GeofenceController;
import com.gigigo.orchextra.control.invoker.InteractorInvoker;
import com.gigigo.orchextra.di.modules.domain.DomainModule;
import com.gigigo.orchextra.di.qualifiers.BackThread;
import com.gigigo.orchextra.domain.interactors.actions.ActionDispatcher;
import com.gigigo.orchextra.domain.interactors.geofences.GeofenceInteractor;
import com.gigigo.orchextra.domain.interactors.user.SaveUserInteractor;
import com.gigigo.orchextra.domain.interactors.config.SendConfigInteractor;
import com.gigigo.orchextra.domain.outputs.BackThreadSpec;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

@Module(includes = DomainModule.class)
public class ControlModule {

  @Provides @Singleton
  GeofenceController provideProximityItemController(InteractorInvoker interactorInvoker,
      GeofenceInteractor geofenceInteractor,
      ActionDispatcher actionDispatcher) {

    return new GeofenceController(interactorInvoker, geofenceInteractor, actionDispatcher);
  }

  @Provides
  @Singleton ConfigObservable providesConfigObservable(){
    return new ConfigObservable();
  }

  @Provides
  @Singleton ConfigController provideConfigController(@BackThread ThreadSpec backThreadSpec,
      InteractorInvoker interactorInvoker,
      SendConfigInteractor sendConfigInteractor,
      ConfigObservable configObservable) {
    return new ConfigController(backThreadSpec, interactorInvoker, sendConfigInteractor, configObservable);
  }

  @Provides @Singleton AuthenticationController provideAuthenticationController(
      InteractorInvoker interactorInvoker, SaveUserInteractor saveUserInteractor,
      @BackThread ThreadSpec backThreadSpec){

    return new AuthenticationController(interactorInvoker, saveUserInteractor, backThreadSpec);
  }

  @Singleton @Provides @BackThread ThreadSpec provideBackThread(){
    return new BackThreadSpec();
  }


}
