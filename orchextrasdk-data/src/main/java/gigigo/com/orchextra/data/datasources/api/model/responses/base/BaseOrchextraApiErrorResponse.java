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

package gigigo.com.orchextra.data.datasources.api.model.responses.base;

import com.gigigo.ggglib.network.responses.ApiGenericErrorResponse;
import com.google.gson.annotations.SerializedName;

public class BaseOrchextraApiErrorResponse<JSONData>
    extends ApiGenericErrorResponse<OrchextraApiErrorResponse> {

  @SerializedName("status") private boolean status;
  @SerializedName("error") private OrchextraApiErrorResponse error;

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override public OrchextraApiErrorResponse getError() {
    return this.error;
  }

  @Override public void setError(OrchextraApiErrorResponse orchextraApiErrorResponse) {
    this.error = error;
  }
}