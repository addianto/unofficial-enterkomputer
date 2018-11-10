package to.adian.unofficialenterkomputer.repository;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import to.adian.unofficialenterkomputer.data.ProductRemoteDataSource;
import to.adian.unofficialenterkomputer.model.Category;
import to.adian.unofficialenterkomputer.model.Product;

public class ProductRepository {

    private static final String TAG = ProductRepository.class.getName();
    private static ProductRepository instance;
    private ProductRemoteDataSource dataSource;

    public ProductRepository(ProductRemoteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized ProductRepository getInstance(ProductRemoteDataSource dataSource) {
        if (instance == null) {
            Log.d(TAG, "Creating the product repository for the first time");
            instance = new ProductRepository(dataSource);
        }

        return instance;
    }

    public LiveData<List<Product>> getProductsByCategory(String endpoint) {
        return dataSource.getProductsByEndpoint(endpoint);
    }
}
