package to.adian.unofficialenterkomputer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import to.adian.unofficialenterkomputer.adapter.CategoryAdapter;
import to.adian.unofficialenterkomputer.data.AppDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppDatabase.getInstance(getContext());
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView view = fragment.findViewById(R.id.category_list);
        view.setAdapter(new CategoryAdapter());

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
