package com.gigigo.orchextra.dataprovision.actions;

import com.gigigo.gggjavalib.business.model.BusinessObject;
import com.gigigo.orchextra.dataprovision.actions.datasource.ActionsDataSource;
import com.gigigo.orchextra.domain.dataprovider.ActionsDataProvider;
import com.gigigo.orchextra.domain.entities.ActionCriteria;
import com.gigigo.orchextra.domain.entities.actions.strategy.BasicAction;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 9/12/15.
 */
public class ActionsDataProviderImpl implements ActionsDataProvider {

  private final ActionsDataSource actionsDataSource;

  public ActionsDataProviderImpl(ActionsDataSource actionsDataSource) {
    this.actionsDataSource = actionsDataSource;
  }

  public BusinessObject<BasicAction> obtainAction(ActionCriteria actionCriteria){
    return actionsDataSource.obtainAction(actionCriteria);
  }

}
