package gigigo.com.orchextra.data.datasources.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 10/2/16.
 */
public class BeaconRegionEventRealm extends RealmObject {

  public static final String CODE_FIELD_NAME = "code";

  private String uuid;
  private int minor;
  private int major;
  private String eventType;
  private String actionRelated;
  private boolean active;
  private boolean actionRelatedCancelable;
  private long timeStampt;
  @PrimaryKey private String code;

  public BeaconRegionEventRealm(BeaconRegionRealm beaconRegionRealm) {
    this.code = beaconRegionRealm.getCode();
    this.uuid = beaconRegionRealm.getUuid();
    this.major = beaconRegionRealm.getMajor();
    this.minor = beaconRegionRealm.getMinor();
    this.eventType = beaconRegionRealm.getEventType();
    this.actionRelated = beaconRegionRealm.getActionRelated();
    this.actionRelatedCancelable = beaconRegionRealm.isActionRelatedCancelable();
    this.active = beaconRegionRealm.isActive();
    timeStampt = System.currentTimeMillis();
  }

  public BeaconRegionEventRealm() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public int getMinor() {
    return minor;
  }

  public void setMinor(int minor) {
    this.minor = minor;
  }

  public int getMajor() {
    return major;
  }

  public void setMajor(int major) {
    this.major = major;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public String getActionRelated() {
    return actionRelated;
  }

  public void setActionRelated(String actionRelated) {
    this.actionRelated = actionRelated;
  }

  public boolean isActionRelatedCancelable() {
    return actionRelatedCancelable;
  }

  public void setActionRelatedCancelable(boolean actionRelatedCancelable) {
    this.actionRelatedCancelable = actionRelatedCancelable;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public long getTimeStampt() {
    return timeStampt;
  }

  public void setTimeStampt(long timeStampt) {
    this.timeStampt = timeStampt;
  }
}
