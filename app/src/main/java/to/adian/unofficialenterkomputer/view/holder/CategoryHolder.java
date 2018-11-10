package to.adian.unofficialenterkomputer.view.holder;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.model.Category;

/**
 * This view holder class binds {@link Category} object to its corresponding
 * list view item.
 */
public class CategoryHolder extends RecyclerView.ViewHolder {

    private static final String TAG = CategoryHolder.class.getName();
    private Button button;

    /**
     * Creates the view holder.
     *
     * @param button reference to the button in the list item.
     */
    public CategoryHolder(@NonNull Button button) {
        super(button.getRootView());
        this.button = button;
    }

    /**
     * Binds the data object {@link Category} to the attributes of button
     * view.
     *
     * @param category the category object that contains name of a category
     * @param listener click handler whenever the button is clicked
     */
    public void bind(Category category, View.OnClickListener listener) {
        Log.v(TAG, String.format("Binding %s to the button.",
                category.getName()));

        button.setText(category.getName());
        button.setOnClickListener(listener);
        button.setTag(category);
    }
}
