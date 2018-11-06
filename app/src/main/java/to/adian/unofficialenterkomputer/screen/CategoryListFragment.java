package to.adian.unofficialenterkomputer.screen;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.adapter.CategoryListAdapter;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.data.CategoryRepository;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;

public class CategoryListFragment extends Fragment {

    private static final String TAG = CategoryListFragment.class.getName();
    private CategoryListViewModel categoryListViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        CategoryListViewModelFactory factory = getCategoryListViewModelFactory(getContext());
        categoryListViewModel = ViewModelProviders.of(this, factory)
                .get(CategoryListViewModel.class);

        ListAdapter listAdapter = new CategoryListAdapter();
        RecyclerView categoryList = view.findViewById(R.id.category_list);
        categoryList.setAdapter(listAdapter);

        subscribeUI(listAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void subscribeUI(final ListAdapter listAdapter) {
        categoryListViewModel.getCategories().observe(getViewLifecycleOwner(),
                categories -> {
                    if (categories != null) {
                        listAdapter.submitList(categories);
                    }
                });
        Log.d(TAG, "CategoryListViewModel has been set to observe this fragment (?)");
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
