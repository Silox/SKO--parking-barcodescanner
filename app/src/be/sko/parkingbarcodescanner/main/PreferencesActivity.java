package be.sko.parkingbarcodescanner.main;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

public class PreferencesActivity extends AbstractSherlockPreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
