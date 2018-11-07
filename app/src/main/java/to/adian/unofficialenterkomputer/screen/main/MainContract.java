package to.adian.unofficialenterkomputer.screen.main;

import to.adian.unofficialenterkomputer.screen.BasePresenter;
import to.adian.unofficialenterkomputer.screen.BaseView;

/**
 * Specifies the contract between the view and the presenter for the main
 * screen.
 */
public interface MainContract {

    interface Presenter extends BasePresenter {
        // TODO
    }

    interface View extends BaseView<Presenter> {

        void openMenuDrawer();

        void closeMenuDrawer();
    }
}
