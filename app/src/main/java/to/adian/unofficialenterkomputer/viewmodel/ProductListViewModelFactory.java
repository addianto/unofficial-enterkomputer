package to.adian.unofficialenterkomputer.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import to.adian.unofficialenterkomputer.repository.CategoryRepository;
import to.adian.unofficialenterkomputer.repository.ProductRepository;

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductListViewModelFactory(ProductRepository productRepository,
                                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductListViewModel(
                productRepository, categoryRepository);
    }
}
