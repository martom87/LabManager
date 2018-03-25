package com.example.android.labmanager.ui.factories;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.android.labmanager.R;
import com.example.android.labmanager.db.DataBaseRealm;
import com.example.android.labmanager.ui.activity_property_card.PropertyCardPresenter;

/**
 * Created by marcinek on 24.03.2018.
 */

public class SimpleDialogFactory {

    Context context;
    PropertyCardPresenter propertyCardPresenter;
    DataBaseRealm dataBaseRealm;
    int deletedCid;


    public SimpleDialogFactory(Context context, PropertyCardPresenter propertyCardPresenter, DataBaseRealm dataBaseRealm, int deletedCid) {
        this.context = context;
        this.propertyCardPresenter = propertyCardPresenter;
        this.dataBaseRealm = dataBaseRealm;
        this.deletedCid = deletedCid;
    }

    public void makeDialog(final String dialogName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (dialogName.equals("askToOverrideCompound")) {
            builder.setMessage(R.string.compoundWasSaved).setTitle("Overwrite");
        } else if (dialogName.equals("askIfRemoveCompound")) {
            builder.setMessage(R.string.makeSureToRemoveCmp).setTitle("Delete");
        } else if (dialogName.equals("askIfMakeEmptyBackup")) {
            builder.setMessage(R.string.makeSureToMakeEmptyBackup).setTitle("EmptyBackup");
        }

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogName.equals("askToOverrideCompound")) {
                    propertyCardPresenter.saveCompoundToDb();
                    Toast.makeText(context, R.string.compoundEdit, Toast.LENGTH_SHORT).show();
                } else if (dialogName.equals("askIfRemoveCompound")) {
                    dataBaseRealm.deleteCompoundsFromDb(deletedCid);
                    Toast.makeText(context, R.string.deletedCompound, Toast.LENGTH_SHORT).show();
                } else if (dialogName.equals("askIfMakeEmptyBackup")) {
                    Activity activity = (Activity) context;
                    IntentFactory intentFactory = new IntentFactory(activity);
                    intentFactory.goToActivity("backupActivity");
                }
            }
        });
        builder.setNegativeButton("no", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
