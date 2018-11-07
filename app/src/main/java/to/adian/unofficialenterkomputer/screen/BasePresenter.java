package to.adian.unofficialenterkomputer.screen;

/**
 * Defines the common interface for a class that will serve as a presenter
 * according to MVP (Model-View-Presenter) design pattern.
 */
public interface BasePresenter {

    /**
     * Performs instructions when the view associated with this presenter
     * is active.
     */
    void start();
}
