package to.adian.unofficialenterkomputer.handler;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import to.adian.unofficialenterkomputer.R;

public class TopLevelNavigationHandler implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = TopLevelNavigationHandler.class.getName();
    private static final String LOG_SELECTED_FMT = "Selected %s menu item";

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String title = String.valueOf(item.getTitle());

        switch (item.getItemId()) {
            case R.id.menu_browse:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                break;
            case R.id.menu_simulate:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                break;
            case R.id.menu_tracking:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                break;
            case R.id.menu_best_seller:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                break;
            default:
                Log.e(TAG, String.format(LOG_SELECTED_FMT, "Unknown"));
                break;
        }

        return true;
    }
}
