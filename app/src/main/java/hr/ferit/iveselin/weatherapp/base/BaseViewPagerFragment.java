package hr.ferit.iveselin.weatherapp.base;

import android.support.v4.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

public abstract class BaseViewPagerFragment extends Fragment {

    protected LatLng location;

    public abstract void changeLocation(LatLng location);

}
