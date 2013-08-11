package be.sko.parkingbarcodescanner.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendCodeTask extends AsyncTask<String, Void, Boolean> {

    private final ProgressDialog dialog;
    private final Activity caller;
    private final String code;

    public SendCodeTask(Activity caller, String code) {
        this.caller = caller;
        this.code = code;
        dialog = new ProgressDialog(caller);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Sending code...");
        this.dialog.show();
    }

    protected Boolean doInBackground(final String... args) {
        try {
            return true;
        } catch (Exception e) {
            Log.e("tag", "error", e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (success) {
            caller.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(caller, "Code sent!", Toast.LENGTH_LONG).show();
                }
            });
            
        } else {
            
            caller.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(caller, "Error, try again.", Toast.LENGTH_LONG).show();
                }
            });
            
        }
    }

}
