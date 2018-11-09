package to.adian.unofficialenterkomputer.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;

public class CategoryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CategoryRepository repository;

    public CategoryListViewModelFactory(CategoryRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CategoryListViewModel(repository);
    }
}
