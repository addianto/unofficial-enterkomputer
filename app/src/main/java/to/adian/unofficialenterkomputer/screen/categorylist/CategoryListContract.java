package to.adian.unofficialenterkomputer.screen.categorylist;

import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.screen.BasePresenter;
import to.adian.unofficialenterkomputer.screen.BaseView;
import to.adian.unofficialenterkomputer.viewmodel.CategoryListViewModel;

public interface CategoryListContract {

    interface Presenter extends BasePresenter {

        // Empty interface
    }

    interface View extends BaseView<Presenter> {

        void showProductList(String endpoint);
    }
}
