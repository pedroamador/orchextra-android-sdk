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

package com.gigigo.orchextra.dataprovision.authentication.datasource;

import com.gigigo.gggjavalib.business.model.BusinessObject;
import com.gigigo.orchextra.domain.model.entities.credentials.ClientAuthCredentials;
import com.gigigo.orchextra.domain.model.entities.authentication.ClientAuthData;
import com.gigigo.orchextra.domain.model.entities.authentication.Crm;
import com.gigigo.orchextra.domain.model.entities.credentials.SdkAuthCredentials;
import com.gigigo.orchextra.domain.model.entities.authentication.SdkAuthData;


public interface SessionDBDataSource {
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean saveSdkAuthCredentials(SdkAuthCredentials sdkAuthCredentials);
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean saveSdkAuthResponse(SdkAuthData sdkAuthData);
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean saveClientAuthCredentials(ClientAuthCredentials clientAuthCredentials);
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean saveClientAuthResponse(ClientAuthData clientAuthData);
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean saveUser(Crm crm);
  //TODO LIB_CRUNCH orchextrasdk-domain
  BusinessObject<ClientAuthData> getSessionToken();
  //TODO LIB_CRUNCH orchextrasdk-domain
  BusinessObject<SdkAuthData> getDeviceToken();
  //TODO LIB_CRUNCH orchextrasdk-domain
  BusinessObject<Crm> getCrm();
  //TODO LIB_CRUNCH orchextrasdk-domain
  boolean storeCrm(Crm crm);
  //TODO LIB_CRUNCH orchextrasdk-domain
  void clearAuthenticatedUser();
}
