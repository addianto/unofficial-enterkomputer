package to.adian.unofficialenterkomputer.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.CategoryRepository;
import to.adian.unofficialenterkomputer.data.ProductNetworkDataSource;
import to.adian.unofficialenterkomputer.data.ProductRepository;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModelFactory;

/**
 * Provides static, utility methods for providing dependencies.
 */
public class Injector {

    private static final String TAG = Injector.class.getName();

    /**
     * Default private constructor of {@code Injector} class.
     */
    private Injector() {
        // No operations
    }

    public static CategoryListViewModelFactory getCategoryListViewModelFactory(@NonNull Context context) {
        CategoryRepository repository = getCategoryRepository(context);
        return new CategoryListViewModelFactory(repository);
    }

    public static ProductListViewModelFactory getProductListViewModelFactory(@NonNull Context context) {
        ProductRepository repository = getProductRepository(context);
        return new ProductListViewModelFactory(repository);
    }

    private static CategoryRepository getCategoryRepository(Context context) {
        Log.d(TAG, "Getting a category repository");
        return CategoryRepository.getInstance(
                AppDatabase.getInstance(context).categoryDao()
        );
    }

    private static ProductRepository getProductRepository(Context context) {
        Log.d(TAG, "Getting a product repository");
        return ProductRepository.getInstance(
                AppDatabase.getInstance(context).productDao(),
                ProductNetworkDataSource.getInstance(context)
        );
    }
}
