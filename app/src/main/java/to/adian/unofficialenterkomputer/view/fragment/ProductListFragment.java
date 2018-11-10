package to.adian.unofficialenterkomputer.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.view.adapter.ProductAdapter;
import to.adian.unofficialenterkomputer.util.Injector;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModelFactory;

import static java.util.Objects.requireNonNull;

public class ProductListFragment extends Fragment {

    public static final String ARG_ENDPOINT = "ENDPOINT";
    private static final String TAG = ProductListFragment.class.getName();

    private ProductListViewModelFactory factory;
    private ProductListViewModel viewModel;
    private ProgressBar loadingBar;
    private RecyclerView productList;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "About to create product list fragment");
        View view = inflater.inflate(R.layout.fragment_product_list, container,
                false);
        factory = Injector.getProductListViewModelFactory(requireNonNull(getContext()));
        viewModel = ViewModelProviders.of(this, factory)
                .get(ProductListViewModel.class);

        loadingBar = view.findViewById(R.id.loading);
        productList = view.findViewById(R.id.product_list_view);

        viewModel.isLoading().observe(this, loading -> {
            if (loading) {
                loadingBar.setVisibility(View.VISIBLE);
                productList.setVisibility(View.GONE);
            } else {
                loadingBar.setVisibility(View.GONE);
                productList.setVisibility(View.VISIBLE);
            }
        });

        String endpoint = getArguments().getString(ARG_ENDPOINT);
        ListAdapter adapter = new ProductAdapter();
        viewModel.getProducts(endpoint).observe(this, products -> {
            if (products != null && !products.isEmpty()) {
                adapter.submitList(products);
            }
        });
        productList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
