package tiepdvph30311.fpoly.lab8_android2.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tiepdvph30311.fpoly.lab8_android2.Fragment.AddNoteFragment;
import tiepdvph30311.fpoly.lab8_android2.Fragment.NotesListFragment;

// MyPagerAdapter.java
// MyPagerAdapter.java
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] tabTitles = new String[]{"Add Note", "Notes List"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AddNoteFragment();
            case 1:
                return new NotesListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
