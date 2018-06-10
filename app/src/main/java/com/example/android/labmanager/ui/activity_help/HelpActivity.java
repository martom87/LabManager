package com.example.android.labmanager.ui.activity_help;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android.labmanager.App;
import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends MenuActivity implements MenuDrawer {


    //TODO change Icon move to strings all text and title
    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        setTitle();
        ButterKnife.bind(this);
    }

    @Override
    public void invokeMenuDrawer() {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_help, null, false);
        drawer.addView(contentView, 0);
    }

    @Override
    public void setTitle() {
        textViewToolbar.setText("About");
    }
}
