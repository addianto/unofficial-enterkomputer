package to.adian.unofficialenterkomputer.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import to.adian.unofficialenterkomputer.data.ProductRepository;

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ProductRepository repository;

    public ProductListViewModelFactory(ProductRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T viewModel = (T) new ProductListViewModel(repository);

        return viewModel;
    }
}
