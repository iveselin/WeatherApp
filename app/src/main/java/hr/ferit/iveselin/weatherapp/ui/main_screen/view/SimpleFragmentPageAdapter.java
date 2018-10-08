package hr.ferit.iveselin.weatherapp.ui.main_screen.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.iveselin.weatherapp.base.BaseViewPagerFragment;

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private List<String> tabTitles = new ArrayList<>();
    private List<BaseViewPagerFragment> fragments = new ArrayList<>();

    public SimpleFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTabTitles(List<String> stringList) {
        tabTitles = stringList;
    }

    public void addFragment(BaseViewPagerFragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public String getPageTitle(int position) {
        return tabTitles.get(position);
    }


    public void sendLocationToFragments(LatLng location) {
        for (BaseViewPagerFragment fragment : fragments) {
            fragment.changeLocation(location);
        }
    }
}
