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

package gigigo.com.orchextra.data.datasources.api.stats;

import com.gigigo.ggglib.network.executors.NetworkExecutor;
import gigigo.com.orchextra.data.datasources.api.service.OrchextraApiService;
import orchextra.javax.inject.Provider;

public class StatsDataSourceImp {

  private final OrchextraApiService orchextraApiService;
  private final Provider<NetworkExecutor> serviceExecutorProvider;

  public StatsDataSourceImp(OrchextraApiService orchextraApiService,
      Provider<NetworkExecutor> serviceExecutorProvider) {
    this.orchextraApiService = orchextraApiService;
    this.serviceExecutorProvider = serviceExecutorProvider;
  }

  public void sendCompletedAction(String trackId) {
    NetworkExecutor serviceExecutor = serviceExecutorProvider.get();

    serviceExecutor.call(
        orchextraApiService.sendCompletedAction(trackId)); //ApiActionConfirmationResponse
  }
}
