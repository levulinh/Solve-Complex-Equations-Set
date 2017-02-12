package com.mandevices.complexEquationsSet;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

/** {@link PreferenceActivity} to make it light
 */
public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

//            boolean isColored = preference.getSharedPreferences().getBoolean("pref_show_colors",true);
//            MainActivity mainActivity = new MainActivity();
//            mainActivity.updateBottomNavigationColor(isColored);

//            Log.w("Test",preference.getSharedPreferences().getBoolean("pref_show_colors",true)+"");
//            if(preference.getKey().equals("pref_show_colors")){
//            boolean isColored = preference.getSharedPreferences().getBoolean("pref_show_colors",true);
//            MainActivity mainActivity = new MainActivity();
//            mainActivity.updateBottomNavigationColor(!isColored);}
            if (preference instanceof ListPreference) {



                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);
                restartActivity();
            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary("Silent");

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    private static void restartActivity() {

    }

    private static void bindValueWithSummary(Preference preference){
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.settings);
//        getSupportActionBar().setTitle(getResources().getString(R.string.settings));

        Fragment fragment = new SettingScreen();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(savedInstanceState == null){
            //created for the first time
            transaction.add(R.id.content_Settings,fragment,"settings_fragment");
            transaction.commit();
        }else {
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }
    }

    public static class SettingScreen extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_settings);
            bindValueWithSummary(findPreference("pref_choose_language"));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
