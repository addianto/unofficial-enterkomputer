package to.adian.unofficialenterkomputer.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.data.CategoryRepository;

public class CategoryListViewModel extends ViewModel {

    private CategoryRepository categoryRepository;

    public CategoryListViewModel(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public LiveData<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public LiveData<List<Category>> getCategories() {
        return categoryRepository.getCategories();
    }
}
