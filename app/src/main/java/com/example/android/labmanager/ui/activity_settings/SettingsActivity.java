package com.example.android.labmanager.ui.activity_settings;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.labmanager.App;
import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class SettingsActivity extends MenuActivity implements MenuDrawer {

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
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        setTitle();
        settingsPresenter.initialize(this);
    }

    @Override
    protected void onDestroy() {
        Log.e("STATE", "OnDestroy");
        super.onDestroy();

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


}
