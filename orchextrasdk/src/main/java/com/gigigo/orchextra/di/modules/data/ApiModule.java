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

package com.gigigo.orchextra.di.modules.data;

import com.gigigo.ggglib.network.client.NetworkClient;
import com.gigigo.ggglib.network.converters.ErrorConverter;
import com.gigigo.ggglib.network.executors.NetworkExecutor;
import com.gigigo.ggglib.network.retrofit.RetrofitNetworkInterceptor;
import com.gigigo.ggglib.network.retrofit.client.RetrofitNetworkClient;
import com.gigigo.ggglib.network.retrofit.client.RetrofitNetworkClientBuilder;
import com.gigigo.ggglib.network.retrofit.executors.RetrofitNetworkExecutorBuilder;
import com.gigigo.ggglib.network.retry.RetryOnErrorPolicy;
import com.gigigo.orchextra.BuildConfig;
import com.gigigo.orchextra.di.modules.data.qualifiers.AcceptLanguage;
import com.gigigo.orchextra.di.modules.data.qualifiers.ApiVersion;
import com.gigigo.orchextra.di.modules.data.qualifiers.Endpoint;
import com.gigigo.orchextra.di.modules.data.qualifiers.HeadersInterceptor;
import com.gigigo.orchextra.di.modules.data.qualifiers.XAppSdk;
import com.gigigo.orchextra.domain.abstractions.device.OrchextraLogger;
import com.gigigo.orchextra.domain.model.entities.authentication.Session;
import gigigo.com.orchextra.data.datasources.api.interceptors.Headers;
import gigigo.com.orchextra.data.datasources.api.interceptors.Logging;
import gigigo.com.orchextra.data.datasources.api.model.responses.base.GenericErrorOrchextraApiResponse;
import gigigo.com.orchextra.data.datasources.api.service.DefaultRetryOnErrorPolicyImpl;
import gigigo.com.orchextra.data.datasources.api.service.OrchextraApiService;
import java.util.Locale;
import orchextra.dagger.Module;
import orchextra.dagger.Provides;
import orchextra.javax.inject.Singleton;

@Module public class ApiModule {

  @Provides @Singleton @Endpoint String provideEndpoint() {
    return BuildConfig.API_URL;
  }

  @Provides @Singleton @ApiVersion String provideApiVersion() {
    return BuildConfig.API_VERSION;
  }

  @Provides @Singleton

  @XAppSdk String provideXAppSdk() {
    return BuildConfig.X_APP_SDK + "_" + BuildConfig.VERSION_NAME;
  }

  @Provides @Singleton @AcceptLanguage String provideAcceptLanguage() {
    return Locale.getDefault().toString();
  }

  @Provides @Singleton OrchextraApiService provideOrchextraApiService(
      NetworkClient networkClient) {
    return (OrchextraApiService)networkClient.getApiClient();
  }

  @Provides @Singleton RetrofitNetworkInterceptor provideLoggingInterceptor() {
    return new Logging();
  }

  @Provides @Singleton @HeadersInterceptor RetrofitNetworkInterceptor provideHeadersInterceptor(
      @XAppSdk String xAppSdk, @AcceptLanguage String acceptLanguage, Session session) {
    return new Headers(xAppSdk, acceptLanguage, session);
  }

  @Provides NetworkClient provideNetworkClient(@Endpoint String enpoint, @ApiVersion String version,
      @HeadersInterceptor RetrofitNetworkInterceptor headersInterceptor,
      RetrofitNetworkInterceptor loggingInterceptor, OrchextraLogger orchextraLogger) {

    RetrofitNetworkClientBuilder retrofitNetworkClientBuilder =
        new RetrofitNetworkClientBuilder(enpoint + version, OrchextraApiService.class);

    if (orchextraLogger.isNetworkLoggingLevelEnabled()) {
      retrofitNetworkClientBuilder.loggingInterceptor(loggingInterceptor);
    }

    return retrofitNetworkClientBuilder.headersInterceptor(headersInterceptor).build();
  }

  @Provides NetworkExecutor provideNetworkExecutor(NetworkClient networkClient, RetryOnErrorPolicy retryOnErrorPolicy) {
    return new RetrofitNetworkExecutorBuilder(networkClient, GenericErrorOrchextraApiResponse.class)
        .retryOnErrorPolicy(retryOnErrorPolicy)
        .build();
  }

  @Provides @Singleton RetryOnErrorPolicy provideRetryOnErrorPolicy() {
    return new DefaultRetryOnErrorPolicyImpl();
  }
}
