package to.adian.unofficialenterkomputer.handler;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.view.fragment.CategoryListFragment;
import to.adian.unofficialenterkomputer.view.fragment.PlaceHolderFragment;

public class TopLevelNavigationHandler
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = TopLevelNavigationHandler.class.getName();
    private static final String LOG_SELECTED_FMT = "Selected %s menu item";
    private AppCompatActivity activity;

    public TopLevelNavigationHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String title = String.valueOf(item.getTitle());

        switch (item.getItemId()) {
            case R.id.menu_browse:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                showBrowse();
                break;
            case R.id.menu_simulate:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                showSimulate(title);
                break;
            case R.id.menu_tracking:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                showTracking(title);
                break;
            case R.id.menu_best_seller:
                Log.d(TAG, String.format(LOG_SELECTED_FMT, title));
                showBestSeller(title);
                break;
            default:
                Log.e(TAG, String.format(LOG_SELECTED_FMT, "Unknown"));
                break;
        }

        return true;
    }

    private void showBrowse() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new CategoryListFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void showSimulate(String text) {
        Fragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putString(PlaceHolderFragment.ARG_PLACEHOLDER_TEXT, text);
        fragment.setArguments(args);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void showTracking(String text) {
        Fragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putString(PlaceHolderFragment.ARG_PLACEHOLDER_TEXT, text);
        fragment.setArguments(args);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void showBestSeller(String text) {
        Fragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putString(PlaceHolderFragment.ARG_PLACEHOLDER_TEXT, text);
        fragment.setArguments(args);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}
