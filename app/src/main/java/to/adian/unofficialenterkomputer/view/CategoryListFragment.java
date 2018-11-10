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
import to.adian.unofficialenterkomputer.adapter.CategoryAdapter;
import to.adian.unofficialenterkomputer.util.Injector;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;

import static java.util.Objects.requireNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryListFragment.CategoryListInteractionListener} interface
 * to handle interaction events.
 */
public class CategoryListFragment extends Fragment {

    private static final String TAG = CategoryListFragment.class.getName();
    private CategoryListInteractionListener mListener;
    private CategoryListViewModel viewModel;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        RecyclerView categoryList = view.findViewById(R.id.category_list_view);
        viewModel = ViewModelProviders.of(this,
                Injector.getCategoryListViewModelFactory(requireNonNull(getContext())))
                .get(CategoryListViewModel.class);

        ListAdapter adapter = new CategoryAdapter(this);
        viewModel.getCategories().observe(this,
                adapter::submitList);
        categoryList.setAdapter(adapter);

        return view;
    }

    public void onClickCategoryListItem(String endpoint) {
        if (mListener != null) {
            mListener.onClickCategoryListItem(endpoint);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CategoryListInteractionListener) {
            mListener = (CategoryListInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface CategoryListInteractionListener {

        void onClickCategoryListItem(String endpoint);
    }
}
