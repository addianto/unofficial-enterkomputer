package to.adian.unofficialenterkomputer.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.model.Category;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.ViewHolder> {

    public CategoryAdapter() {
        super(new CategoryDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        MaterialButton button = view.findViewById(R.id.category_button);

        return new ViewHolder(button);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = getItem(position);
        holder.bind(category);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = ViewHolder.class.getName();
        private MaterialButton button;

        public ViewHolder(@NonNull MaterialButton button) {
            super(button.getRootView());
            this.button = button;
        }

        void bind(Category category) {
            button.setText(category.getName());
            button.setOnClickListener(view -> Log.d(TAG, String.format(Locale.ENGLISH,
                    "Clicked %s button", category.getName())));
            button.setTag(category);
        }
    }

    static class CategoryDiffUtil extends DiffUtil.ItemCallback<Category> {

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
