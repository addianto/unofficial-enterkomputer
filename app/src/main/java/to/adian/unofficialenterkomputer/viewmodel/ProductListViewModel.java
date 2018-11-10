package to.adian.unofficialenterkomputer.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import to.adian.unofficialenterkomputer.model.Category;
import to.adian.unofficialenterkomputer.model.Product;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.repository.ProductRepository;

public class ProductListViewModel extends ViewModel {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private LiveData<Category> category;

    public ProductListViewModel(ProductRepository productRepository,
                                CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void init(String categoryName) {
        category = categoryRepository.getCategoryByName(categoryName);
    }

    public LiveData<List<Product>> getProducts() {
        return productRepository.getProductsByCategory(category.getValue());
    }
}
