package tiepdvph30311.fpoly.lab8_android2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tiepdvph30311.fpoly.lab8_android2.Fragment.AddNoteFragment;
import tiepdvph30311.fpoly.lab8_android2.Fragment.NotesListFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NotesListFragment();
            case 1:
                return new AddNoteFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Danh sách";
            case 1:
                return "Thêm ghi chú";
            default:
                return null;
        }
    }
}
