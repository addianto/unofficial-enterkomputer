package to.adian.unofficialenterkomputer.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.data.Category;
import to.adian.unofficialenterkomputer.databinding.ListItemCategoryBinding;
import to.adian.unofficialenterkomputer.screen.CategoryListActivity;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = CategoryAdapter.class.getName();
    private final CategoryListActivity activity;

    public CategoryAdapter(CategoryListActivity activity) {
        super(new CategoryDiffUtil());
        this.activity = activity;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ListItemCategoryBinding binding;

        CategoryViewHolder(@NonNull ListItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
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
        holder.binding.setCategory(category);
        holder.binding.setClickListener(view -> {
            Log.d(TAG, "Clicked: " + category.getName());
            activity.showProductList(category.getEndpoint());
        });
        holder.binding.executePendingBindings();
        holder.itemView.setTag(category);
    }
}
