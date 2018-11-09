package to.adian.unofficialenterkomputer.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.ProductListAdapter;
import to.adian.unofficialenterkomputer.databinding.ActivityProductListBinding;
import to.adian.unofficialenterkomputer.util.Injector;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModelFactory;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG = ProductListActivity.class.getName();
    private RecyclerView productList;
    private ProductListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductListBinding binding = DataBindingUtil.setContentView(this,
                        R.layout.activity_product_list);
        productList = binding.productList;

        Intent intent = getIntent();
        String endpoint = intent.getStringExtra(CategoryListActivity.EXTRA_ENDPOINT);

        setupViewModel(endpoint);

        ListAdapter adapter = new ProductListAdapter();
        productList.setAdapter(adapter);

        viewModel.getProducts().observe(this, products -> {
            if (products != null) {
                adapter.submitList(products);
            }
        });
    }

    private void setupViewModel(String endpoint) {
        ProductListViewModelFactory factory = Injector.getProductListViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(ProductListViewModel.class);
        viewModel.init(endpoint);
    }
}
