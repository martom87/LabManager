package com.example.android.labmanager.ui.activity_settings;

import android.content.Context;

import com.example.android.labmanager.db.DataBaseRealm;
import com.example.android.labmanager.ui.activity_backup.BackupPresenter;
import com.example.android.labmanager.utilis.SharedPrefStorage;

import javax.inject.Inject;

public class SettingsPresenter extends BackupPresenter {

    @Inject
    public SettingsPresenter(SharedPrefStorage sharedPrefStorage, DataBaseRealm dataBaseRealm, Context context) {
        super(sharedPrefStorage, dataBaseRealm, context);
    }


}
