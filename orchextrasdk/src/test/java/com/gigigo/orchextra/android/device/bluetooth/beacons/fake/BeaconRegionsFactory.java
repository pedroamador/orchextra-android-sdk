package com.gigigo.orchextra.android.device.bluetooth.beacons.fake;

import com.gigigo.encryptation.Md5Algorithm;
import com.gigigo.orchextra.domain.abstractions.beacons.RegionsProviderListener;
import com.gigigo.orchextra.domain.model.entities.proximity.OrchextraRegion;
import java.util.ArrayList;
import java.util.List;

public class BeaconRegionsFactory {

  private static final String BEACONS_UUID = "e6775403-f0dd-40c4-87db-95e755738ad1";
  private static final int BEACON_MAYOR_10 = 10;
  private static final int BEACON_MAYOR_11 = 1;
  private static final String BEACON_ID_CONCAT_CHAR = "_";

  public static void obtainRegionsToScan(RegionsProviderListener regionsProviderListener) {

    List<OrchextraRegion> regions = new ArrayList<>();

    String regionElevenCodeId = "regionElevenCodeId";
    String regionTenCodeId = "regionTenCodeId";

    String md5ElevenCode = new StringBuffer().append(BEACONS_UUID)
        .append(BEACON_ID_CONCAT_CHAR)
        .append(BEACON_MAYOR_11)
        .toString();
    regionElevenCodeId = Md5Algorithm.calculateMD5(md5ElevenCode.getBytes());

    String md5TenCode = new StringBuffer().append(BEACONS_UUID)
        .append(BEACON_ID_CONCAT_CHAR)
        .append(BEACON_MAYOR_10)
        .toString();
    regionTenCodeId = Md5Algorithm.calculateMD5(md5TenCode.getBytes());

    OrchextraRegion region;
    if (regionTenCodeId != null) {
      region = new OrchextraRegion(regionTenCodeId, BEACONS_UUID, BEACON_MAYOR_10, -1, true);
      regions.add(region);
    }

    if (regionElevenCodeId != null) {
      region = new OrchextraRegion(regionElevenCodeId, BEACONS_UUID, BEACON_MAYOR_11, -1, true);
      regions.add(region);
    }

    if (!regions.isEmpty()) {
      regionsProviderListener.onRegionsReady(regions);
    }
  }
}
