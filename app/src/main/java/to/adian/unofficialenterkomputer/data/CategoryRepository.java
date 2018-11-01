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

    public LiveData<List<Category>> getCategories() {
        LiveData<List<Category>> categories = categoryDao.getCategories();

        return categories;
    }

    public static synchronized CategoryRepository getInstance(CategoryDao categoryDao) {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepository(categoryDao);
            Log.d(TAG, "Instantiated CategoryRepository singleton for the 1st time");
        }

        return categoryRepository;
    }
}
