package to.adian.unofficialenterkomputer.data;

import android.util.Log;

import com.android.volley.toolbox.Volley;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProductRepository {

    private static final String TAG = ProductRepository.class.getName();
    private static volatile ProductRepository instance;
    private ProductDao dao;
    private ProductNetworkDataSource networkDataSource;

    private ProductRepository(ProductDao dao,
                              ProductNetworkDataSource networkDataSource) {
        this.dao = dao;
        this.networkDataSource = networkDataSource;
    }

    public static synchronized ProductRepository getInstance(ProductDao dao,
                                                             ProductNetworkDataSource networkDataSource) {
        if (instance == null) {
            instance = new ProductRepository(dao, networkDataSource);
            Log.d(TAG, "Created product repository for the 1st time");
        }

        return instance;
    }

    public LiveData<List<Product>> getProductsByEndpoint(String url) {
        return networkDataSource.getProductsFromEndpoint(url);
    }
}
