package com.example.android.labmanager.ui.activity_query;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.labmanager.App;
import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;
import com.example.android.labmanager.ui.factories.IntentFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Admin on 2017-07-20.
 */

public class QueryActivity extends MenuActivity implements QueryView, MenuDrawer {


    IntentFactory intentFactory;

    @Nullable
    @BindView(R.id.typeCidNumber)
    EditText typeCidValue;

    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Nullable
    @BindView(R.id.imageViewFlask)
    ImageView erlenmayerFlask;

    @Nullable
    @BindView(R.id.cmpdsDataLoader_progress_bar)
    ProgressBar progressBar;

    @Inject
    QueryPresenter queryPresenter;


    @Optional
    @OnClick(R.id.buttonCompoundDataDownload)

    public void onClick() {
        queryPresenter.setCidInput(String.valueOf(typeCidValue.getText()));
        showProgress();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        setTitle();
        queryPresenter.attachQueryView(this);
        intentFactory = new IntentFactory(this);
        showFlask();

    }

    @Override
    public void invokeMenuDrawer() {

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            View contentView = inflater.inflate(R.layout.activity_query, null, false);
            drawer.addView(contentView, 0);
        } else {
            View contentView = inflater.inflate(R.layout.activity_query_horz, null, false);
            drawer.addView(contentView, 0);
        }


    }

    @Override
    public void setTitle() {
        textViewToolbar.setText(R.string.title_activity_query);
    }


    @Override
    protected void onDestroy() {
        Log.e("STATE", "OnDestroy");
        queryPresenter.detachQueryView();
        super.onDestroy();
    }

    @Override
    public void showErrorMessage(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToCompoundsCardActivity() {
        intentFactory.goToActivity("propertyCardActivity");
    }

    void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void showFlask() {
        Picasso.with(getApplicationContext()).load(R.drawable.erlenmayer)
                .transform(new RoundedCornersTransformation(100, 5))
                .fit().into(erlenmayerFlask);
    }


}




