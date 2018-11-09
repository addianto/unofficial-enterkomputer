package to.adian.unofficialenterkomputer.screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.CategoryAdapter;
import to.adian.unofficialenterkomputer.databinding.ActivityCategoryListBinding;
import to.adian.unofficialenterkomputer.util.Injector;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;

public class CategoryListActivity extends AppCompatActivity {

    public static final String EXTRA_ENDPOINT = "endpoint";
    private static final String TAG = CategoryListActivity.class.getName();
    private DrawerLayout drawerLayout;
    private RecyclerView categoryList;
    private CategoryListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCategoryListBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_category_list);
        drawerLayout = binding.drawerLayout;
        categoryList = binding.categoryList;

        setUpViewModel();
        setUpDrawer();

        ListAdapter adapter = new CategoryAdapter(this);
        categoryList.setAdapter(adapter);

        viewModel.getCategories().observe(this, categories -> {
            if (categories != null) {
                adapter.submitList(categories);
            }
        });
    }

    private void setUpViewModel() {
        CategoryListViewModelFactory factory = Injector.getCategoryListViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(CategoryListViewModel.class);
    }

    private void setUpDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showProductList(String endpoint) {
        Log.d(TAG, "About to show product list from endpoint: " + endpoint);
        Intent intent = new Intent(this,
                ProductListActivity.class);
        intent.putExtra(EXTRA_ENDPOINT, endpoint);
        startActivity(intent);
    }
}
