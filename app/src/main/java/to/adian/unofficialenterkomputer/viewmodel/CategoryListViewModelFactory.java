package to.adian.unofficialenterkomputer.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import to.adian.unofficialenterkomputer.data.CategoryRepository;

public class CategoryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static final String TAG = CategoryListViewModelFactory.class.getName();

    private final CategoryRepository categoryRepository;

    public CategoryListViewModelFactory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T viewModel = (T) new CategoryListViewModel(categoryRepository);
        Log.d(TAG, "Created view model for category list");

        return viewModel;
    }
}
