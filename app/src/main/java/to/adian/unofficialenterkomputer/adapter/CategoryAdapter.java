package to.adian.unofficialenterkomputer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.databinding.ListItemCategoryBinding;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.ViewHolder> {

    public CategoryAdapter() {
        super(new DiffUtil.ItemCallback<Category>() {

            @Override
            public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCategoryBinding binding = ListItemCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = getItem(position);
        holder.bind(category);
        holder.itemView.setTag(category);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemCategoryBinding binding;

        ViewHolder(ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Category category) {
            binding.setCategory(category);
            binding.executePendingBindings();
        }
    }
}
