package to.adian.unofficialenterkomputer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.databinding.ListItemCategoryBinding;

public class CategoryListAdapter extends ListAdapter<Category, CategoryListAdapter.CategoryViewHolder> {

    public CategoryListAdapter() {
        super(new CategoryDiffUtil());
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ListItemCategoryBinding binding;

        CategoryViewHolder(ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Category category) {
            binding.setCategory(category);
            binding.executePendingBindings();
        }
    }

    static class CategoryDiffUtil extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                 int viewType) {
        ListItemCategoryBinding view = ListItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,
                                 int position) {
        Category category = getItem(position);
        holder.bind(category);
        holder.itemView.setTag(category);
    }
}
