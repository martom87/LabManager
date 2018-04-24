package com.example.android.labmanager.ui.activity_settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;
import com.example.android.labmanager.ui.activity_settings.change_account_fragment.ChangeAccountFragment;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class SettingsActivity extends MenuActivity implements MenuDrawer {

    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Optional
    @OnClick(R.id.goToChangeLanguage)

    public void OnClick() {
        openChangeAccountFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        ButterKnife.bind(this);
        setTitle();
    }

    private void openChangeAccountFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_placeholder, new ChangeAccountFragment());

        fragmentTransaction.commit();
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
