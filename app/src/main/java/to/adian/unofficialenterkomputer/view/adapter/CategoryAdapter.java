package to.adian.unofficialenterkomputer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.model.Category;
import to.adian.unofficialenterkomputer.view.fragment.CategoryListFragment;
import to.adian.unofficialenterkomputer.view.holder.CategoryHolder;

public class CategoryAdapter extends ListAdapter<Category, CategoryHolder> {

    private CategoryListFragment fragment;

    public CategoryAdapter(CategoryListFragment fragment) {
        super(new CategoryDiffUtil());
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        Button button = view.findViewById(R.id.category_button);

        return new CategoryHolder(button);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = getItem(position);
        holder.bind(category,
                view -> fragment.onClickCategoryListItem(category.getEndpoint()));
    }

    private static class CategoryDiffUtil extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getEndpoint().equals(newItem.getEndpoint());
        }
    }
}
