package to.adian.unofficialenterkomputer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.handler.TopLevelNavigationHandler;
import to.adian.unofficialenterkomputer.view.fragment.CategoryListFragment;
import to.adian.unofficialenterkomputer.view.fragment.ProductListFragment;

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

        Log.d(TAG, "Displaying category list");
        Fragment categoryList = new CategoryListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, categoryList)
                .commit();
    }

    @Override
    public void onClickCategoryListItem(String endpoint) {
        Log.d(TAG, "Going to create fragment for displaying data from "
                + endpoint);

        Fragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ProductListFragment.ARG_ENDPOINT, endpoint);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
