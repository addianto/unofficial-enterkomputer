package to.adian.unofficialenterkomputer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.data.Product;
import to.adian.unofficialenterkomputer.databinding.ListItemProductBinding;

public class ProductListAdapter extends ListAdapter<Product, ProductListAdapter.ProductViewHolder> {

    public ProductListAdapter() {
        super(new ProductDiffUtil());
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ListItemProductBinding binding;

        ProductViewHolder(@NonNull ListItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.setProduct(product);
            binding.executePendingBindings();
        }
    }

    static class ProductDiffUtil extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getName() == newItem.getName()
                    && oldItem.getPrice() == newItem.getPrice();
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        ListItemProductBinding binding = ListItemProductBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.bind(product);
        holder.itemView.setTag(product);
    }
}
