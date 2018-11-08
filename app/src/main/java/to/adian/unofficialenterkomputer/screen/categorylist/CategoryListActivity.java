package to.adian.unofficialenterkomputer.screen.categorylist;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.ProductListAdapter;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.CategoryRepository;
import to.adian.unofficialenterkomputer.databinding.ActivityCategoryListBinding;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;

public class CategoryListActivity extends AppCompatActivity implements CategoryListContract.View {

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

        CategoryListAdapter adapter = new CategoryListAdapter(this);

        viewModel.getCategories().observe(this, categories -> {
            if (categories != null) {
                categoryList.setAdapter(adapter);
            }
        });
    }

    private void setUpViewModel() {
        CategoryListViewModelFactory factory = getCategoryListViewModelFactory(this);
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

    @Override
    public void showProductList(String endpoint) {
        /*
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra(ProductListActivity.ENDPOINT, endpoint);
        startActivity(intent);
        */
    }

    private static CategoryListViewModelFactory getCategoryListViewModelFactory(Context context) {
        CategoryListViewModelFactory factory = new CategoryListViewModelFactory(
                CategoryRepository.getInstance(
                        AppDatabase.getInstance(context).categoryDao()
                )
        );
        Log.d(TAG, "Created CategoryListViewModelFactory");

        return factory;
    }
}
