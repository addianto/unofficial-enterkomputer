package to.adian.unofficialenterkomputer.view;

import androidx.appcompat.app.AppCompatActivity;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.handler.TopLevelNavigationHandler;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements CategoryListFragment.CategoryListInteractionListener {

    private static final String TAG = MainActivity.class.getName();
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Creating activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Setting up bottom navigation view");
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(
                new TopLevelNavigationHandler());
    }

    @Override
    public void onClickCategoryListItem(String endpoint) {
        Bundle args = new Bundle();
        args.putString("ENDPOINT", endpoint);
        Log.d(TAG, "Going to create fragment for displaying data from "
                + endpoint);
    }
}
