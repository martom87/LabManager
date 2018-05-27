package com.example.android.labmanager.ui.factories;

import android.app.Activity;
import android.content.Intent;

import com.example.android.labmanager.ui.activity_backup.BackupActivity;
import com.example.android.labmanager.ui.activity_list.CompoundsListActivity;
import com.example.android.labmanager.ui.activity_property_card.PropertyCardActivity;
import com.example.android.labmanager.ui.activity_query.QueryActivity;
import com.example.android.labmanager.ui.activity_settings.SettingsActivity;

/**
 * Created by marcinek on 24.03.2018.
 */

public class IntentFactory {


    private Intent intent;
    private Activity activity;


    public IntentFactory(Activity activity) {
        this.activity = activity;
    }

    public void goToActivity(String activityName) {

        if (activityName.equals("queryActivity")) {
            intent = new Intent(activity, QueryActivity.class);
        } else if (activityName.equals("propertyCardActivity")) {
            intent = new Intent(activity, PropertyCardActivity.class);
        } else if (activityName.equals("compoundsListActivity")) {
            intent = new Intent(activity, CompoundsListActivity.class);
        } else if (activityName.equals("backupActivity")) {
            intent = new Intent(activity, BackupActivity.class);
        } else if (activityName.equals("settingsActivity")) {
            intent = new Intent(activity, SettingsActivity.class);
        } else if (activityName.equals("restart")) {
            intent = activity.getIntent();
        }
        activity.startActivity(intent);
        activity.finish();
    }


}
