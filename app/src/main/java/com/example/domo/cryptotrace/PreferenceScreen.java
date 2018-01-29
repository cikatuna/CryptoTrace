package com.example.domo.cryptotrace;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import android.support.annotation.Nullable;

/**
 * Created by Domo on 26.1.2018..
 */

public class PreferenceScreen extends PreferenceFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Kreiranje metode da stalno sprema preference
        addPreferencesFromResource(R.xml.preference_screen);




        }
}
