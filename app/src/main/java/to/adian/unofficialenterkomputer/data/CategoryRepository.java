package to.adian.unofficialenterkomputer.data;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CategoryRepository {

    private static final String TAG = CategoryRepository.class.getName();
    private static volatile CategoryRepository categoryRepository;
    private CategoryDao categoryDao;

    private CategoryRepository(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public static synchronized CategoryRepository getInstance(CategoryDao categoryDao) {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepository(categoryDao);
            Log.d(TAG, "Created category repository for the 1st time");
        }

        return categoryRepository;
    }

    public LiveData<Category> getCategory(int id) {
        LiveData<Category> category = categoryRepository.getCategory(id);

        return category;
    }

    public LiveData<List<Category>> getCategories() {
        LiveData<List<Category>> categories = categoryDao.getCategories();

        return categories;
    }

}
