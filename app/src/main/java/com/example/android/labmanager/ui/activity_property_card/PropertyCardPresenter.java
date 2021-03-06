package com.example.android.labmanager.ui.activity_property_card;


import android.content.Context;

import com.example.android.labmanager.R;
import com.example.android.labmanager.db.DataBaseRealm;
import com.example.android.labmanager.model.Property;
import com.example.android.labmanager.network.pubChem.PubChemClient;
import com.example.android.labmanager.utilis.SharedPrefStorage;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by marcinek on 30.10.2017.
 */

public class PropertyCardPresenter {

    private PropertyCardView propertyCardView;
    private Property property;
    private SharedPrefStorage sharedPrefStorage;
    private DataBaseRealm dataBaseRealm;
    private PubChemClient pubChemClient;
    private boolean propertyIsEmpty;
    private Context context;

    @Inject

    public PropertyCardPresenter(SharedPrefStorage sharedPrefStorage, DataBaseRealm dataBaseRealm, PubChemClient pubChemClient, Context context) {
        this.sharedPrefStorage = sharedPrefStorage;
        this.dataBaseRealm = dataBaseRealm;
        this.pubChemClient = pubChemClient;
        this.context = context;
    }


    public void attachPropertyCardView(PropertyCardView propertyCardView) {
        this.propertyCardView = propertyCardView;
    }

    public void detachPropertyCardView() {
        this.propertyCardView = null;
    }


    List<String> writeCompoundData() {
        String name = property.getIUPACName();
        String formula = property.getMolecularFormula();
        String massUnit = context.getResources().getString(R.string.activity_property_no_mass_unit);
        String mass = (String.valueOf((property.getMolecularWeight()) + massUnit));
        String cID = String.valueOf(property.getCID());
        List<String> compoundsData = Arrays.asList(name, formula, mass, cID);
        return compoundsData;
    }

    List<String> writeDefaultCompoundData() {
        String name = context.getResources().getString(R.string.activity_property_no_data_display);
        String formula = name;
        String mass = name;
        String cID = name;
        List<String> compoundsData = Arrays.asList(name, formula, mass, cID);
        return compoundsData;
    }

    List<String> putProperty() {
        List<String> compoundsData;
        property = getProperty();
        propertyIsEmpty = (property == null);
        if (propertyIsEmpty) {
            compoundsData = writeDefaultCompoundData();
        } else {
            compoundsData = writeCompoundData();
        }
        return compoundsData;
    }


    public Property getProperty() {
        property = sharedPrefStorage.readProperty();
        return property;
    }


    public String getBmUrl() {
        String url = "";
        if (propertyIsEmpty) {
            url = "Empty";
        } else {
            url = pubChemClient.getService().getPng(property.getCID()).request().url().toString();
        }
        return url;
    }


    void saveCompoundDontAsk(String store) {
        if (!propertyIsEmpty) {
            boolean compoundWasNotSaved = compoundIsSaved(Integer.parseInt(writeCompoundData().get(3)));
            setCompoundStore(store);
            if (compoundWasNotSaved) {
                saveCompoundToDb();
                propertyCardView.showCompoundWasSaved(R.string.infoCompoundWasSaved);
            } else {
                propertyCardView.askIfOverrideCompound();
            }
        } else {
            propertyCardView.showErrorMessage(R.string.neverQueriedCompound);
        }
    }

    boolean compoundIsSaved(int checkedCid) {
        boolean noCompound = ((dataBaseRealm.getRealm()).where(Property.class)
                .equalTo("cID", checkedCid).count() == 0);
        if (noCompound) {
            return true;
        } else {
            return false;
        }
    }

    void setCompoundStore(String store) {

        if (store.isEmpty()) {
            store = context.getResources().getString(R.string.activity_property_no_storage);
            property.setStore(store);
        } else {
            property.setStore(store);
        }


    }

    public void saveCompoundToDb() {
        dataBaseRealm.saveCompoundsDataToDb(property);
    }


    void showCompound() {
        propertyCardView.displayPropertyData();
        propertyCardView.displayPropertyImage(getBmUrl());
    }

}
