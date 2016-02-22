package com.gigigo.orchextra.dataprovision.authentication.datasource;

import com.gigigo.gggjavalib.business.model.BusinessObject;
import com.gigigo.orchextra.domain.model.entities.authentication.ClientAuthData;
import com.gigigo.orchextra.domain.model.entities.credentials.Credentials;
import com.gigigo.orchextra.domain.model.entities.authentication.SdkAuthData;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 9/12/15.
 */
public interface AuthenticationDataSource {

  BusinessObject<SdkAuthData> authenticateSdk(Credentials credentials);

  BusinessObject<ClientAuthData> authenticateUser(Credentials credentials);
}