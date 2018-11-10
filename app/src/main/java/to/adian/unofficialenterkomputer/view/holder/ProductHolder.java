package to.adian.unofficialenterkomputer.view.holder;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.model.Product;

/**
 * This view holder class binds {@link Product} object to its corresponding
 * list view item.
 */
public class ProductHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ProductHolder.class.getName();
    private TextView productName;
    private TextView productPrice;

    public ProductHolder(@NonNull TextView productName, @NonNull TextView productPrice) {
        super(productName.getRootView());
        this.productName = productName;
        this.productPrice = productPrice;
    }

    /**
     * Binds the data object {@link Product} to the attributes of list item
     * in recycler view.
     *
     * @param product the product object that contains name and price
     */
    public void bind(Product product) {
        Log.v(TAG, String.format("Binding %s to the list item view.",
                product.getName()));

        productName.setText(product.getName());
        productPrice.setText(Integer.toString(product.getPrice()));
    }
}
