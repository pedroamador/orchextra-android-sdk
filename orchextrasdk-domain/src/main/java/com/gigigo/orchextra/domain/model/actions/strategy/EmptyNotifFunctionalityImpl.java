package com.gigigo.orchextra.domain.model.actions.strategy;

public class EmptyNotifFunctionalityImpl implements NotifFunctionality {

    @Override
    public Notification getNotification() {
//        throw new UnsupportedOperationException(); // Empty action crashes
        return null;
    }

    @Override
    public boolean isSupported() {
        return false;
    }
}
