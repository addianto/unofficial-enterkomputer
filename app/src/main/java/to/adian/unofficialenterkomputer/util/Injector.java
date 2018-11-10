package to.adian.unofficialenterkomputer.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.ProductRemoteDataSource;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.repository.ProductRepository;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModelFactory;
import to.adian.unofficialenterkomputer.viewmodel.ProductListViewModelFactory;

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

    public static ProductListViewModelFactory getProductListViewModelFactory(@NonNull Context context,
                                                                             @NonNull ProductRemoteDataSource dataSource) {
        Log.d(TAG, "Getting a view model factory for product list");
        ProductRepository productRepository = getProductRepository(dataSource);
        CategoryRepository categoryRepository = getCategoryRepository(context);
        return new ProductListViewModelFactory(productRepository,
                categoryRepository);
    }

    private static CategoryRepository getCategoryRepository(Context context) {
        Log.d(TAG, "Getting a category repository");
        return CategoryRepository.getInstance(
                AppDatabase.getInstance(context).categoryDao()
        );
    }

    private static ProductRepository getProductRepository(ProductRemoteDataSource dataSource) {
        Log.d(TAG, "Getting a product repository");
        return ProductRepository.getInstance(dataSource);
    }
}
