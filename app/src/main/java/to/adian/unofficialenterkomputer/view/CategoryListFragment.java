package to.adian.unofficialenterkomputer.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.CategoryAdapter;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CategoryListFragment extends Fragment {

    private static final String TAG = CategoryListFragment.class.getName();
    private OnFragmentInteractionListener mListener;
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
                getViewModelFactory(getContext()))
                .get(CategoryListViewModel.class);

        CategoryAdapter adapter = new CategoryAdapter();
        viewModel.getCategories().observe(this,
                adapter::submitList);
        categoryList.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static CategoryListViewModelFactory getViewModelFactory(Context context) {
        return new CategoryListViewModelFactory(
                CategoryRepository.getInstance(
                        AppDatabase.getInstance(context).categoryDao()
                )
        );
    }
}
