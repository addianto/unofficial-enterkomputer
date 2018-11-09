package to.adian.unofficialenterkomputer.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import to.adian.unofficialenterkomputer.model.Category;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;

public class CategoryListViewModel extends ViewModel {

    private final CategoryRepository repository;

    public CategoryListViewModel(CategoryRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Category>> getCategories() {
        return repository.getCategories();
    }
}
