package be.sko.parkingbarcodescanner.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.view.MenuItem;

import be.sko.parkingbarcodescanner.utils.IntentIntegrator;
import be.sko.parkingbarcodescanner.utils.IntentResult;

public class ScannerActivity extends AbstractSherlockActivity {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState A saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Check if internet is available
        checkForNetwork();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Checks for network connectivity and closes the app when there is none
     */
    private void checkForNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.no_network_title)
                    .setMessage(R.string.no_network)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    public void scan(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            new SendCodeTask(this, scanResult.getContents()).execute();
        }

    }
}
