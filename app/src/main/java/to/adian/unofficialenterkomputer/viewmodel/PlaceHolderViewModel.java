package to.adian.unofficialenterkomputer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceHolderViewModel extends ViewModel {

    private MutableLiveData<String> placeHolderText;

    public PlaceHolderViewModel() {
        // Default empty public constructor
    }

    public void init(String text) {
        placeHolderText = new MutableLiveData<>();
        placeHolderText.setValue(text);
    }

    public LiveData<String> getPlaceHolderText() {
        return placeHolderText;
    }
}
