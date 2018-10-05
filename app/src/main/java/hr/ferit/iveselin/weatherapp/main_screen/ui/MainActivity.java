package hr.ferit.iveselin.weatherapp.main_screen.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.iveselin.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final LatLngBounds CRO_BOUNDS = new LatLngBounds(new LatLng(13.6569755388, 42.47999136),
            new LatLng(19.3904757016, 46.5037509222));

    @BindView(R.id.location_input_text)
    AutoCompleteTextView locationInput;

    @BindView(R.id.weather_tabs)
    TabLayout weatherTabs;

    @BindView(R.id.weather_view_pager)
    ViewPager weatherPager;


    private GeoDataClient geoDataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setUi();
    }

    private void setUi() {
        geoDataClient = Places.getGeoDataClient(this);
        PlaceAutocompleteAdapter autocompleteAdapter = new PlaceAutocompleteAdapter(this, geoDataClient, CRO_BOUNDS, null);
        locationInput.setAdapter(autocompleteAdapter);
    }
}
