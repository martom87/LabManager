package com.example.android.labmanager.ui.activity_settings;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.labmanager.App;
import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;
import com.example.android.labmanager.ui.factories.IntentFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


 public class SettingsActivity extends MenuActivity implements MenuDrawer, SettingsView {

    IntentFactory intentFactory;

    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Nullable
    @BindView(R.id.changeAccount)
    Button changeAccount;

    @Optional
    @OnClick(R.id.changeAccount)

    public void OnClick() {
        settingsPresenter.disconnectClient();
      //  showAccountWasChanged();
        recreate();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        setTitle();
        settingsPresenter.attachSettingsView(this);
        settingsPresenter.initialize(this);
        intentFactory = new IntentFactory(this);
    }

    @Override
    protected void onDestroy() {
        Log.e("STATE", "OnDestroy");
        super.onDestroy();
        settingsPresenter.detachSettingsView();

    }


    @Override
    public void invokeMenuDrawer() {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_settings, null, false);
        drawer.addView(contentView, 0);
    }

    @Override
    public void setTitle() {
        textViewToolbar.setText("Settings");
    }

    @Override
    public void onBackPressed() {
        intentFactory.goToActivity("queryActivity");


    }

    @Override
    public void showAccountWasChanged() {
        Toast.makeText(getApplicationContext(), "Please choose the account", Toast.LENGTH_SHORT).show();
    }
}
