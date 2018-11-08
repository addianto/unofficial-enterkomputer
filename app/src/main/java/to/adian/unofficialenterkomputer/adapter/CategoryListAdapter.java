package to.adian.unofficialenterkomputer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.databinding.ListItemCategoryBinding;
import to.adian.unofficialenterkomputer.screen.categorylist.CategoryListContract;

public class CategoryListAdapter extends ListAdapter<Category, CategoryListAdapter.CategoryViewHolder> {

    private CategoryListContract.View view;

    public CategoryListAdapter(CategoryListContract.View view) {
        super(new CategoryDiffUtil());
        this.view = view;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ListItemCategoryBinding binding;

        CategoryViewHolder(@NonNull ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Category category) {
            binding.setCategory(category);
            binding.setClickListener(() -> view.showProductList(category.getEndpoint()));
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
        ListItemCategoryBinding binding = ListItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,
                                 int position) {
        Category category = getItem(position);
        holder.bind(category);
        holder.itemView.setTag(category);
    }
}
