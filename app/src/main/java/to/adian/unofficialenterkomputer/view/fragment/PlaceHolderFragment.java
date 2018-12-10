package to.adian.unofficialenterkomputer.view.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import to.adian.unofficialenterkomputer.R;
import to.adian.unofficialenterkomputer.viewmodel.PlaceHolderViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceHolderFragment extends Fragment {

    public static final String ARG_PLACEHOLDER_TEXT = "PLACEHOLDER_TEXT";
    private static final String TAG = PlaceHolderFragment.class.getName();
    private PlaceHolderViewModel viewModel;
    private TextView placeHolderView;

    public PlaceHolderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "Executing onCreate() callback");
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        viewModel = ViewModelProviders.of(this)
                .get(PlaceHolderViewModel.class);
        viewModel.init(args.getString(ARG_PLACEHOLDER_TEXT));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Executing onCreateView() callback");
        View fragment = inflater.inflate(R.layout.fragment_placeholder,
                container, false);

        placeHolderView = fragment.findViewById(R.id.placeholder_view);
        viewModel.getPlaceHolderText().observe(this,
                placeHolderView::setText);

        return fragment;
    }
}
