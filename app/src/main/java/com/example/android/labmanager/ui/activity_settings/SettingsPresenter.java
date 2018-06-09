package com.example.android.labmanager.ui.activity_settings;

import android.content.Context;

import com.example.android.labmanager.db.DataBaseRealm;
import com.example.android.labmanager.ui.activity_backup.BackupPresenter;
import com.example.android.labmanager.ui.activity_backup.BackupView;
import com.example.android.labmanager.utilis.SharedPrefStorage;

import javax.inject.Inject;

public class SettingsPresenter extends BackupPresenter {
    private SettingsView settingsView;

    @Inject
    public SettingsPresenter(SharedPrefStorage sharedPrefStorage, DataBaseRealm dataBaseRealm, Context context) {
        super(sharedPrefStorage, dataBaseRealm, context);
    }

    public void attachSettingsView(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    public void detachSettingsView() {
        this.settingsView = null;
    }

    @Override
    public void disconnectClient() {
        super.disconnectClient();
        if (!(isClientConnected())) {
            settingsView.showAccountWasChanged();
        }

    }

    @Override
    public boolean isClientConnected() {
        return super.isClientConnected();

    }


}
