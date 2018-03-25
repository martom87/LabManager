package com.example.android.labmanager.ui.activity_property_card;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.labmanager.App;
import com.example.android.labmanager.R;
import com.example.android.labmanager.ui.activity_menu.MenuActivity;
import com.example.android.labmanager.ui.activity_menu.MenuDrawer;
import com.example.android.labmanager.ui.factories.SimpleDialogFactory;
import com.example.android.labmanager.ui.activity_query.QueryActivity;
import com.squareup.picasso.Picasso;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by katar on 02.07.2017.
 */

public class PropertyCardActivity extends MenuActivity implements PropertyCardView, MenuDrawer {

    @Nullable
    @BindView(R.id.textViewCompoundsName)
    TextView compoundName;

    @Nullable
    @BindView(R.id.textViewCompoundsFormula)
    TextView compoundFormula;

    @Nullable
    @BindView(R.id.textViewCompoundsMass)
    TextView compoundMass;

    @Nullable
    @BindView(R.id.textViewCompoundsCid)
    TextView compoundCid;

    @Nullable
    @BindView(R.id.imageViewCompoundsPNG)
    ImageView compoundPNG;

    @Nullable
    @BindView(R.id.typeStorePlace)
    EditText storeValue;

    @BindView(R.id.textViewToolbar)
    TextView textViewToolbar;

    @Optional
    @OnClick(R.id.saveDataToRealm)
    public void onClick() {
        propertyCardPresenter.saveCompoundDontAsk(String.valueOf(storeValue.getText()));
    }

    @Inject
    PropertyCardPresenter propertyCardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invokeMenuDrawer();
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        setTitle();
        propertyCardPresenter.attachPropertyCardView(this);
        propertyCardPresenter.showCompound();
    }

    @Override
    public void invokeMenuDrawer() {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_compounds_card, null, false);
        drawer.addView(contentView, 0);
    }

    @Override
    public void setTitle() {
        textViewToolbar.setText("Compound Card");
    }

    @Override
    protected void onDestroy() {
        propertyCardPresenter.detachPropertyCardView();
        Log.e("STATE", "OnDestroy");
        super.onDestroy();
    }

    @Override
    public void displayPropertyData() {
        List<String> compoundsData = propertyCardPresenter.putProperty();
        compoundName.setText(compoundsData.get(0));
        compoundFormula.setText(compoundsData.get(1));
        compoundMass.setText(compoundsData.get(2));
        compoundCid.setText(compoundsData.get(3));

        displayPropertyImage(propertyCardPresenter.getBmUrl());


    }

    @Override
    public void displayPropertyImage(String url) {
        if (url.equals("Empty")) {
            Picasso.with(getApplicationContext()).load(R.drawable.benzene)
                    .transform(new RoundedCornersTransformation(100, 5))
                    .fit().into(compoundPNG);
        } else {
            Picasso.with(getApplicationContext()).load(url)
                    .transform(new RoundedCornersTransformation(100, 5))
                    .fit().into(compoundPNG);
        }

    }


    @Override
    public void askIfOverrideCompound() {
        SimpleDialogFactory simpleDialogFactory = new SimpleDialogFactory(this, propertyCardPresenter, null, 0);
        simpleDialogFactory.makeDialog("askToOverrideCompound");
    }

    @Override
    public void showCompoundWasSaved(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, QueryActivity.class);
        startActivity(intent);
        finish();

    }


}
