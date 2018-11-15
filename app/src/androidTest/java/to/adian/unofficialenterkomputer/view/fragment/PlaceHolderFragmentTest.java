package to.adian.unofficialenterkomputer.view.fragment;

import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import to.adian.unofficialenterkomputer.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PlaceHolderFragmentTest {

    @Test
    public void smokeTest() {
        Bundle args = new Bundle();
        args.putString(PlaceHolderFragment.ARG_PLACEHOLDER_TEXT, "Test");

        FragmentScenario<PlaceHolderFragment> scenario =
                FragmentScenario.launchInContainer(PlaceHolderFragment.class,
                        args);

        scenario.onFragment(fragment ->
                onView(withId(R.id.placeholder_view))
                        .check(matches(withText("Test"))));
    }
}