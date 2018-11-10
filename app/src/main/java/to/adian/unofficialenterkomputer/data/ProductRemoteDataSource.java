package to.adian.unofficialenterkomputer.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import to.adian.unofficialenterkomputer.model.Product;

public interface ProductRemoteDataSource {

    LiveData<List<Product>> getProductsByEndpoint(String url);
}
