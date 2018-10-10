package hr.ferit.iveselin.weatherapp.ui.main_screen.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
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
import hr.ferit.iveselin.weatherapp.ui.map_screen.view.MapActivity;

public class MainActivity extends AppCompatActivity implements MainScreenInterface.View {

    private static final String TAG = "MainActivity";

    private static final LatLngBounds CRO_BOUNDS = new LatLngBounds(new LatLng(13.6569755388, 42.47999136),
            new LatLng(19.3904757016, 46.5037509222));
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final String KEY_PICKED_LONGITUDE = "picked_longitude";
    private static final String KEY_PICKED_LATITUDE = "picked_latitude";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1111;
    private static final int MAP_REQUEST_CODE = 1234;

    public static Intent getResultIntent(double longitude, double latitude) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_PICKED_LONGITUDE, longitude);
        resultIntent.putExtra(KEY_PICKED_LATITUDE, latitude);
        return resultIntent;
    }

    @BindView(R.id.location_input_text)
    AutoCompleteTextView locationInput;

    @BindView(R.id.weather_tabs)
    TabLayout weatherTabs;
    @BindView(R.id.weather_view_pager)
    ViewPager weatherPager;

    private GeoDataClient geoDataClient;

    private SimpleFragmentPageAdapter pageAdapter;

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

    private void setUi() {

        geoDataClient = Places.getGeoDataClient(this);
        PlaceAutocompleteAdapter autocompleteAdapter = new PlaceAutocompleteAdapter(this, geoDataClient, CRO_BOUNDS, null);
        locationInput.setAdapter(autocompleteAdapter);
        locationInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchClicked();
                    return true;
                }
                return false;
            }
        });

        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Current");
        tabTitles.add("5 days");

        pageAdapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());
        pageAdapter.setTabTitles(tabTitles);
        pageAdapter.addFragment(CurrentWeatherView.newInstance());
        pageAdapter.addFragment(FiveDaysWeatherView.newInstance());

        weatherPager.setAdapter(pageAdapter);
        weatherTabs.setupWithViewPager(weatherPager);

        presenter.viewReady();
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
    public void setLocation(LatLng location) {
        pageAdapter.sendLocationToFragments(location);
    }

    @Override
    public void getLocationFromAddress(String searchCityString) {
        Geocoder geocoder = new Geocoder(this);

        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchCityString, 1);
        } catch (IOException e) {
            presenter.currentLocationNotFound();
            Log.d(TAG, "getAddressLocation: failed error:" + e.getMessage());
        }

        if (list.size() > 0) {
            presenter.currentLocationFound(list.get(0).getLatitude(), list.get(0).getLongitude());
        } else {
            presenter.currentLocationNotFound();
        }
    }

    @Override
    public void findCurrentLocation() {
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            Task location = locationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location currentLocation = task.getResult();
                        if (currentLocation != null) {
                            presenter.currentLocationFound(task.getResult().getLatitude(), task.getResult().getLongitude());
                        }
                    } else {
                        Log.d(TAG, "onComplete: unsuccessful location");
                        presenter.currentLocationNotFound();
                    }
                }
            });

        } catch (SecurityException e) {
            presenter.currentLocationNotFound();
            Log.d(TAG, "getDeviceLocation: failed call to locate device: error " + e.getMessage());
        }
    }


    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyInputError() {
        locationInput.setError(getString(R.string.empty_input_error_text));
    }

    @OnClick(R.id.location_search)
    void searchClicked() {
        locationInput.clearFocus();
        try {
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(locationInput.getWindowToken(), 0);
        } catch (NullPointerException e) {
            Log.d(TAG, "searchClicked: " + e.getLocalizedMessage());
        }

        presenter.searchPressed(locationInput.getText().toString());
    }

    @OnClick(R.id.location_input_map)
    void mapClicked() {
        presenter.mapPressed();
    }

    @Override
    public void showMap(LatLng location) {
        startActivityForResult(MapActivity.getLaunchIntent(this, location.longitude, location.latitude), MAP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == MAP_REQUEST_CODE) {
                double latitude = data.getDoubleExtra(KEY_PICKED_LATITUDE, 0);
                double longitude = data.getDoubleExtra(KEY_PICKED_LONGITUDE, 0);
                presenter.receivedLocationFromMap(latitude, longitude);
            }
        }
    }
}
