package to.adian.unofficialenterkomputer.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import to.adian.unofficialenterkomputer.data.Product;
import to.adian.unofficialenterkomputer.data.ProductRepository;

public class ProductListViewModel extends ViewModel {

    private ProductRepository repository;
    private MutableLiveData<String> endpoint;

    public ProductListViewModel(ProductRepository repository) {
        this.repository = repository;
        this.endpoint = new MutableLiveData<>();
    }

    public void init(String endpoint) {
        this.endpoint.setValue(endpoint);
    }

    public LiveData<List<Product>> getProducts() {
        return repository.getProductsByEndpoint(endpoint.getValue());
    }

    public LiveData<String> getEndpoint() {
        return endpoint;
    }
}
