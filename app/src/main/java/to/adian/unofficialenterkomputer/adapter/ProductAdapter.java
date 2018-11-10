package to.adian.unofficialenterkomputer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.model.Product;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ViewHolder> {

    public ProductAdapter() {
        super(new ProductDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        TextView productName = view.findViewById(R.id.product_name);
        TextView productPrice = view.findViewById(R.id.product_price);

        return new ViewHolder(productName, productPrice);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = getItem(position);
        holder.bind(product);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = ViewHolder.class.getName();
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(TextView productName, TextView productPrice) {
            super(productName.getRootView());
            this.productName = productName;
            this.productPrice = productPrice;
        }

        void bind(Product product) {
            productName.setText(product.getName());
            productPrice.setText(Integer.toString(product.getPrice()));
        }
    }

    static class ProductDiffUtil extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getPrice() == newItem.getPrice();
        }
    }
}
