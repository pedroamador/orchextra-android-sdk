package gigigo.com.orchextra.data.datasources.db.config;

import com.gigigo.ggglib.mappers.ExternalClassToModelMapper;
import com.gigigo.ggglogger.GGGLogImpl;
import com.gigigo.orchextra.dataprovision.config.model.strategy.ConfigInfoResult;
import com.gigigo.orchextra.domain.model.entities.Vuforia;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraGeofence;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraRegion;
import com.gigigo.orchextra.domain.model.vo.Theme;
import gigigo.com.orchextra.data.datasources.db.NotFountRealmObjectException;
import gigigo.com.orchextra.data.datasources.db.model.BeaconRegionRealm;
import gigigo.com.orchextra.data.datasources.db.model.ConfigInfoResultRealm;
import gigigo.com.orchextra.data.datasources.db.model.GeofenceRealm;
import gigigo.com.orchextra.data.datasources.db.model.ThemeRealm;
import gigigo.com.orchextra.data.datasources.db.model.VuforiaRealm;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 21/12/15.
 */
public class ConfigInfoResultReader {

  private final ExternalClassToModelMapper<BeaconRegionRealm, OrchextraRegion> regionRealmMapper;
  private final ExternalClassToModelMapper<GeofenceRealm, OrchextraGeofence> geofencesRealmMapper;
  private final ExternalClassToModelMapper<VuforiaRealm, Vuforia> vuforiaRealmMapper;
  private final ExternalClassToModelMapper<ThemeRealm, Theme> themeRealmMapper;

  public ConfigInfoResultReader(
      ExternalClassToModelMapper<BeaconRegionRealm, OrchextraRegion> regionRealmMapper,
      ExternalClassToModelMapper<GeofenceRealm, OrchextraGeofence> geofencesRealmMapper,
      ExternalClassToModelMapper<VuforiaRealm, Vuforia> vuforiaRealmMapper,
      ExternalClassToModelMapper<ThemeRealm, Theme> themeRealmMapper) {

    this.regionRealmMapper = regionRealmMapper;
    this.geofencesRealmMapper = geofencesRealmMapper;
    this.vuforiaRealmMapper = vuforiaRealmMapper;
    this.themeRealmMapper = themeRealmMapper;
  }

  public ConfigInfoResult readConfigInfo(Realm realm) {

    ConfigInfoResultRealm config = readConfigObject(realm);

    Vuforia vuforia = vuforiaRealmMapper.externalClassToModel(readVuforiaObject(realm));
    Theme theme = themeRealmMapper.externalClassToModel(readThemeObject(realm));
    List<OrchextraGeofence> geofences = geofencesToModel(readGeofenceObjects(realm));
    List<OrchextraRegion> regions = regionsToModel(readRegionsObjects(realm));

    ConfigInfoResult configInfoResult =
        new ConfigInfoResult.Builder(config.getRequestWaitTime(), geofences, regions, theme,
            vuforia).build();

    GGGLogImpl.log("Retrieved configInfoResult with properties"
        + " \n Theme :"
        + theme.toString()
        + " Vuforia :"
        + vuforia.toString()
        + " Geofences :"
        + geofences.size()
        + " Regions :"
        + regions.size()
        + " Request Time :"
        + config.getRequestWaitTime());

    return configInfoResult;
  }

  private List<OrchextraRegion> regionsToModel(List<BeaconRegionRealm> beaconRegionRealms) {
    List<OrchextraRegion> regions = new ArrayList<>();
    for (BeaconRegionRealm beaconRegionRealm : beaconRegionRealms) {
      regions.add(regionRealmMapper.externalClassToModel(beaconRegionRealm));
    }
    return regions;
  }

  private List<OrchextraGeofence> geofencesToModel(List<GeofenceRealm> geofencesRealm) {
    List<OrchextraGeofence> geofences = new ArrayList<>();
    for (GeofenceRealm geofenceRealm : geofencesRealm) {
      geofences.add(geofencesRealmMapper.externalClassToModel(geofenceRealm));
    }
    return geofences;
  }

  private ConfigInfoResultRealm readConfigObject(Realm realm) {
    ConfigInfoResultRealm configInfo = realm.where(ConfigInfoResultRealm.class).findFirst();
    if (configInfo == null) {
      configInfo = new ConfigInfoResultRealm();
    }
    return configInfo;
  }

  private VuforiaRealm readVuforiaObject(Realm realm) {
    return realm.where(VuforiaRealm.class).findFirst();
  }

  private ThemeRealm readThemeObject(Realm realm) {
    return realm.where(ThemeRealm.class).findFirst();
  }

  private List<GeofenceRealm> readGeofenceObjects(Realm realm) {
    return realm.where(GeofenceRealm.class).findAll();
  }

  private List<BeaconRegionRealm> readRegionsObjects(Realm realm) {
    return realm.where(BeaconRegionRealm.class).findAll();
  }

  public OrchextraGeofence getGeofenceById(Realm realm, String geofenceId) {

    RealmResults<GeofenceRealm> geofenceRealm =
        realm.where(GeofenceRealm.class).equalTo("code", geofenceId).findAll();

    if (geofenceRealm.size() == 0) {
      GGGLogImpl.log("Not found geofence with id: " + geofenceId);
      throw new NotFountRealmObjectException();
    } else {
      GGGLogImpl.log("Found geofence with id: " + geofenceId);
      return geofencesRealmMapper.externalClassToModel(geofenceRealm.first());
    }
  }

  public List<OrchextraRegion> getAllRegions(Realm realm) {
    RealmResults<BeaconRegionRealm> regionRealms = realm.where(BeaconRegionRealm.class).findAll();
    List<OrchextraRegion> regions = new ArrayList<>();

    for (BeaconRegionRealm beaconRegionRealm : regionRealms) {
      regions.add(regionRealmMapper.externalClassToModel(beaconRegionRealm));
    }

    if (regions.size() > 0) {
      GGGLogImpl.log("Retrieved " + regions.size() + " beacon regions");
    } else {
      GGGLogImpl.log("Not Retrieved any region");
    }

    return regions;
  }
}
