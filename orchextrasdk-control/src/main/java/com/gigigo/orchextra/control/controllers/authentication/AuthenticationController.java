package com.gigigo.orchextra.control.controllers.authentication;

import com.gigigo.orchextra.control.InteractorResult;
import com.gigigo.orchextra.control.controllers.base.Controller;
import com.gigigo.orchextra.control.invoker.InteractorExecution;
import com.gigigo.orchextra.control.invoker.InteractorInvoker;
import com.gigigo.orchextra.domain.entities.SdkAuthCredentials;
import com.gigigo.orchextra.domain.interactors.authentication.AuthenticationError;
import com.gigigo.orchextra.domain.interactors.authentication.AuthenticationInteractor;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 4/12/15.
 */
public class AuthenticationController extends Controller<AuthenticationDelegate> {

  private final InteractorInvoker interactorInvoker;
  private final AuthenticationInteractor authenticationInteractor;

  public AuthenticationController(InteractorInvoker interactorInvoker,
      AuthenticationInteractor authenticationInteractor, ThreadSpec mainThreadSpec) {
    super(mainThreadSpec);
    this.interactorInvoker = interactorInvoker;
    this.authenticationInteractor = authenticationInteractor;
  }

  @Override public void onDelegateAttached() {
    getDelegate().onControllerReady();
  }

  public void authenticate(String apiKey, String apiSecret) {

    authenticationInteractor.setSdkAuthCredentials(new SdkAuthCredentials(apiKey, apiSecret));

    new InteractorExecution<>(authenticationInteractor).result(new InteractorResult<SdkAuthCredentials>() {
      @Override public void onResult(SdkAuthCredentials result) {

        getDelegate().authenticationSuccessful();

      }
    }).error(AuthenticationError.class, new InteractorResult<AuthenticationError>() {
      @Override public void onResult(AuthenticationError error) {

        getDelegate().authenticationError();

      }
    }).execute(interactorInvoker);
  }

}