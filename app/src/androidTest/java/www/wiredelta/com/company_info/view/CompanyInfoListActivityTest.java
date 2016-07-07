package www.wiredelta.com.company_info.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import www.wiredelta.com.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CompanyInfoListActivityTest {

    @Rule
    public ActivityTestRule<CompanyInfoListActivity> mActivityTestRule = new ActivityTestRule<>(CompanyInfoListActivity.class);

    @Test
    public void performFilterClickTest() throws Exception{
        Thread.sleep(2000);
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.filter),  isDisplayed()));
        actionMenuItemView.perform(click());
        onView(withText("Filter")).check(matches(isDisplayed()));
    }

    @Test
    public void performScrollandClickOnList() throws Exception {
        Thread.sleep(2000);
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withId(R.id.listView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(5, click()));
    }
}
