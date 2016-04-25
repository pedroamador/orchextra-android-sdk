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

package com.gigigo.orchextra.domain.dataprovider;

import com.gigigo.gggjavalib.business.model.BusinessObject;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraBeacon;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraGeofence;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraRegion;
import java.util.List;

public interface ProximityLocalDataProvider {
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraRegion> obtainRegion(OrchextraRegion orchextraRegion);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraRegion> storeRegion(OrchextraRegion orchextraRegion);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraRegion> deleteRegion(OrchextraRegion orchextraRegion);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraBeacon> storeBeaconEvent(OrchextraBeacon beacon);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<List<OrchextraRegion>> getBeaconRegionsForScan();

  void purgeOldBeaconEventsWithRequestTime(int requestTime);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraRegion> updateRegionWithActionId(OrchextraRegion orchextraRegion);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraGeofence> storeGeofenceEvent(OrchextraGeofence geofence);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraGeofence> deleteGeofenceEvent(String geofenceId);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraGeofence> obtainSavedGeofenceInDatabase(String geofenceId);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraGeofence> obtainGeofenceEvent(OrchextraGeofence geofence);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<OrchextraGeofence> updateGeofenceWithActionId(OrchextraGeofence geofence);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<List<OrchextraBeacon>> getNotStoredBeaconEvents(
      List<OrchextraBeacon> orchextraBeacons);
  //TODO LIB_CRUNCH gggJavaLib
  BusinessObject<List<OrchextraGeofence>> obtainGeofencesForRegister();
}
