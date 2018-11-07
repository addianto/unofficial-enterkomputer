package to.adian.unofficialenterkomputer.screen;

/**
 * Defines the common interface for a class that will serve as a view according
 * to MVP (Model-View-Presenter) design pattern.
 *
 * @param <T>
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Associates the view with the given {@code presenter}.
     *
     * @param presenter
     */
    void setPresenter(T presenter);
}
