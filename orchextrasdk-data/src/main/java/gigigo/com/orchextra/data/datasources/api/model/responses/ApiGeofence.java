package gigigo.com.orchextra.data.datasources.api.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 15/12/15.
 */
public class ApiGeofence extends ApiProximityPoint {

  @Expose @SerializedName("point") private ApiPoint point;

  @Expose @SerializedName("radius") private int radius;

  public ApiPoint getPoint() {
    return point;
  }

  public void setPoint(ApiPoint point) {
    this.point = point;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}
