package to.adian.unofficialenterkomputer.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.ProductRemoteDataSource;
import to.adian.unofficialenterkomputer.data.ProductWebService;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;

public class Injector {

    private static final String TAG = Injector.class.getName();

    private Injector() {
        // Default private constructor
    }

    public static CategoryListViewModelFactory getCategoryListViewModelFactory(@NonNull Context context) {
        Log.d(TAG, "Getting a view model factory for category list");
        CategoryRepository repository = getCategoryRepository(context);
        return new CategoryListViewModelFactory(repository);
    }

    public static ProductRemoteDataSource getProductWebService(Context context) {
        Log.d(TAG, "Getting a Web service helper for obtaining products data");
        return new ProductWebService(context);
    }

    private static CategoryRepository getCategoryRepository(Context context) {
        Log.d(TAG, "Getting a category repository");
        return CategoryRepository.getInstance(
                AppDatabase.getInstance(context).categoryDao()
        );
    }
}
