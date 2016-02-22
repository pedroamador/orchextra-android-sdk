package gigigo.com.orchextra.data.datasources.db.model.mappers;

import com.gigigo.ggglib.mappers.Mapper;
import com.gigigo.ggglib.mappers.MapperUtils;

import java.util.ArrayList;
import java.util.List;

import gigigo.com.orchextra.data.datasources.db.model.KeyWordRealm;
import io.realm.RealmList;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 22/12/15.
 */
public class KeyWordRealmMapper implements Mapper<String, KeyWordRealm> {

  @Override public KeyWordRealm modelToExternalClass(String s) {
    KeyWordRealm keyWordRealm = new KeyWordRealm(s);
    return keyWordRealm;
  }

  @Override public String externalClassToModel(KeyWordRealm keyWordRealm) {
    return keyWordRealm.getKeyword();
  }

  public RealmList<KeyWordRealm> stringKeyWordsToRealmList(List<String> keywords) {

    RealmList<KeyWordRealm> keyWordRealms = new RealmList<>();
    if (keywords == null ){
      return keyWordRealms;
    }

    for (String keyword:keywords){
      keyWordRealms.add(MapperUtils.checkNullDataRequest(this, keyword));
    }
    return keyWordRealms;
  }

  public List<String> realmKeyWordsToStringList(RealmList<KeyWordRealm> keyWordRealms) {

    List<String> keyWords = new ArrayList<>();
    if (keyWordRealms == null ){
      return keyWords;
    }

    for (KeyWordRealm keyWordRealm:keyWordRealms){
      keyWords.add(MapperUtils.checkNullDataResponse(this, keyWordRealm));
    }
    return keyWords;
  }
}