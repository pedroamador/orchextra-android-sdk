/*
 * Created by Orchextra
 *
 * Copyright (C) 2017 Gigigo Mobile Services SL
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

package gigigo.com.orchextrasdk.demo.geofences;

import com.google.android.gms.maps.model.LatLng;

public final class Geofence {

  private final LatLng center;
  private final double radius;

  public Geofence(LatLng center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  public LatLng getCenter() {
    return center;
  }

  public double getRadius() {
    return radius;
  }
}