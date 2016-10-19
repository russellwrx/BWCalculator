package com.demo.russell.bwsecurity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import static android.R.attr.key;

/**
 * Created by russell on 16/10/2016.
 */

public class Preferences  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Preference);
        setContentView(R.layout.activity_no_detail);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyPreferenceFragment myPreferenceFragment = new MyPreferenceFragment();
        fragmentTransaction.add(android.R.id.content, myPreferenceFragment, "SETTINGS_FRAGMENT");
        fragmentTransaction.commit();

    }


    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings_screen);



            //
        }
    }
}
