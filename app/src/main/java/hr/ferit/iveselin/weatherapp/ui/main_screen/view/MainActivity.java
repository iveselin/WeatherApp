package hr.ferit.iveselin.weatherapp.ui.main_screen.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.ui.current_weather.view.CurrentWeatherView;
import hr.ferit.iveselin.weatherapp.ui.five_days_weather.view.FiveDaysWeatherView;
import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;
import hr.ferit.iveselin.weatherapp.ui.main_screen.presentation.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainScreenInterface.View {

    private static final String TAG = "MainActivity";

    private static final LatLngBounds CRO_BOUNDS = new LatLngBounds(new LatLng(13.6569755388, 42.47999136),
            new LatLng(19.3904757016, 46.5037509222));

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1111;
    public static final float DEFAULT_ZOOM = 15f;

    @BindView(R.id.location_input_text)
    AutoCompleteTextView locationInput;

    @BindView(R.id.weather_tabs)
    TabLayout weatherTabs;
    @BindView(R.id.weather_view_pager)
    ViewPager weatherPager;


    private GeoDataClient geoDataClient;
    private SimpleFragmentPageAdapter pageAdapter;
    private boolean locationPermissionGranted;

    private MainScreenInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter = new MainPresenter();
        presenter.setView(this);

        setUi();
    }


    @Override
    public void checkLocationPermission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            presenter.locationPermissionGranted(true);
        } else {
            askLocationPermission();
        }
    }

    @Override
    public void askLocationPermission() {
        String[] permissions = {FINE_LOCATION, COURSE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            presenter.locationPermissionGranted(false);
                            return;
                        }
                    }
                    presenter.locationPermissionGranted(true);
                }
            }
        }
    }

    @Override
    public void showMap() {

    }

    private void setUi() {

        geoDataClient = Places.getGeoDataClient(this);
        PlaceAutocompleteAdapter autocompleteAdapter = new PlaceAutocompleteAdapter(this, geoDataClient, CRO_BOUNDS, null);
        locationInput.setAdapter(autocompleteAdapter);

        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Current");
        tabTitles.add("5 days");

        pageAdapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());
        pageAdapter.setTabTitles(tabTitles);
        pageAdapter.addFragment(CurrentWeatherView.newInstance("Osijek"));
        pageAdapter.addFragment(FiveDaysWeatherView.newInstance());

        weatherPager.setAdapter(pageAdapter);
        weatherTabs.setupWithViewPager(weatherPager);

        presenter.viewReady();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.location_input_map)
    void mapClicked() {
        presenter.mapPressed();
    }


}
