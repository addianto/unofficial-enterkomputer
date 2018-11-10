package to.adian.unofficialenterkomputer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.ProductAdapter;
import to.adian.unofficialenterkomputer.data.ProductWebService;
import to.adian.unofficialenterkomputer.util.Injector;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModel;

import static java.util.Objects.requireNonNull;

public class ProductListFragment extends Fragment {

    public static final String ARG_ENDPOINT = "ENDPOINT";
    private static final String TAG = ProductListFragment.class.getName();
    private ProductListViewModel viewModel;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        Bundle args = getArguments();
        RecyclerView productList = view.findViewById(R.id.product_list_view);
        String categoryName = args.getString(ARG_ENDPOINT);
        viewModel = ViewModelProviders.of(this,
                Injector.getProductListViewModelFactory(requireNonNull(getContext()),
                        new ProductWebService(requireNonNull(getContext()))))
                .get(ProductListViewModel.class);
        viewModel.init(categoryName);

        ListAdapter adapter = new ProductAdapter();
        viewModel.getProducts().observe(this,
                adapter::submitList);
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
