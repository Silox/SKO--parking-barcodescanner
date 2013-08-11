/**
 *
 * @author Tom Naessens 
 * Tom.Naessens@UGent.be 
 * 3de Bachelor Informatica
 * Universiteit Gent
 *
 */

package be.sko.parkingbarcodescanner.main;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;

import com.actionbarsherlock.view.MenuItem;

public class ActionBarHelper {

    public static boolean onOptionsItemSelected(MenuItem item, Activity activity) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent settingsIntent = new Intent(activity, PreferencesActivity.class);
                activity.startActivity(settingsIntent);
                return true;
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                Intent upIntent = new Intent(activity, ScannerActivity.class); // Default to Main view

                if (activity.getIntent().getStringExtra("class") != null) {
                    try {
                        upIntent = new Intent(activity, Class.forName(activity.getIntent().getStringExtra("class")));
                    } catch (ClassNotFoundException ex) {
                    }
                }

                if (NavUtils.shouldUpRecreateTask(activity, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(activity)
                            .addNextIntent(upIntent)
                            .startActivities();
                    activity.finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(activity, upIntent);
                }
                return true;
            default:
                return true;
        }
    }

}
