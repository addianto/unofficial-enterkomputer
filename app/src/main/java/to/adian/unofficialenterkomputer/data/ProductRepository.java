package to.adian.unofficialenterkomputer.data;

import android.util.Log;

public class ProductRepository {

    private static final String TAG = ProductRepository.class.getName();
    private static volatile ProductRepository instance;
    private ProductDao dao;

    private ProductRepository(ProductDao dao) {
        this.dao = dao;
    }

    public static synchronized ProductRepository getInstance(ProductDao dao) {
        if (instance == null) {
            instance = new ProductRepository(dao);
            Log.d(TAG, "Created product repository for the 1st time");
        }

        return instance;
    }
}
