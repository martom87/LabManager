package com.example.android.labmanager.ui.activity_menu;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.labmanager.R;
import com.example.android.labmanager.db.DataBaseRealm;
import com.example.android.labmanager.ui.factories.IntentFactory;
import com.example.android.labmanager.ui.factories.SimpleDialogFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //TODO change navigation bar item labels to string resources
    //TODO Test the app on tablet screen
    protected ActionBarDrawerToggle toggle;
    //private Realm realm;
    IntentFactory intentFactory;


    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Inject
    DataBaseRealm dataBaseRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        onCreateDrawer();
        intentFactory = new IntentFactory(this);
    }

    protected void onCreateDrawer() {

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (!((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL)) {
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.START);
                }
            });

            toggle.setHomeAsUpIndicator(R.drawable.baseline_dehaze_white_36dp);

        }

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_compoundsList) {
            intentFactory.goToActivity("compoundsListActivity");
        } else if (id == R.id.nav_query) {
            intentFactory.goToActivity("queryActivity");
        } else if (id == R.id.nav_queriedCompoundCard) {
            intentFactory.goToActivity("propertyCardActivity");
        } else if (id == R.id.nav_makeGoogleBackup) {
            askIfMakeEmptyBackup();
        } else if (id == R.id.nav_settings) {
            intentFactory.goToActivity("settingsActivity");
        } else if (id == R.id.nav_about) {
            intentFactory.goToActivity("help");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void askIfMakeEmptyBackup() {
        if (dataBaseRealm.getRealm().isEmpty()) {
            SimpleDialogFactory simpleDialogFactory = new SimpleDialogFactory(this, null, null, 0);
            simpleDialogFactory.makeDialog("askIfMakeEmptyBackup");

        } else
            intentFactory.goToActivity("backupActivity");


    }

}


