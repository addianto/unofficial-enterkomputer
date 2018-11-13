package to.adian.unofficialenterkomputer.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import to.adian.unofficialenterkomputer.model.Product;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.repository.ProductRepository;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<Boolean> loading;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductListViewModel(ProductRepository productRepository,
                                CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        loading = new MutableLiveData<>();
        loading.setValue(false);
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    public LiveData<List<Product>> getProducts(String endpoint) {
        loading.setValue(true);
        LiveData<List<Product>> products =
                productRepository.getProductsByCategory(endpoint);
        loading.setValue(false);

        return products;
    }
}
