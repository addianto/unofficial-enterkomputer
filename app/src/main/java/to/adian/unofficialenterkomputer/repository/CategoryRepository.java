package to.adian.unofficialenterkomputer.repository;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import to.adian.unofficialenterkomputer.data.CategoryDao;
import to.adian.unofficialenterkomputer.model.Category;

public class CategoryRepository {

    private static final String TAG = CategoryRepository.class.getName();
    private static CategoryRepository instance;
    private CategoryDao dao;

    private CategoryRepository(CategoryDao dao) {
        this.dao = dao;
    }

    public static synchronized CategoryRepository getInstance(CategoryDao dao) {
        if (instance == null) {
            Log.d(TAG, "Creating the category repository for the first time");
            instance = new CategoryRepository(dao);
        }

        return instance;
    }

    public LiveData<List<Category>> getCategories() {
        return dao.getCategories();
    }
}
