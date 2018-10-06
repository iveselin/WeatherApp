package hr.ferit.iveselin.weatherapp.ui.main_screen.view;

import android.support.design.widget.TabLayout;
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

    @BindView(R.id.location_input_text)
    AutoCompleteTextView locationInput;

    @BindView(R.id.weather_tabs)
    TabLayout weatherTabs;

    @BindView(R.id.weather_view_pager)
    ViewPager weatherPager;


    private GeoDataClient geoDataClient;

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

        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Current");
        tabTitles.add("5 days");

        SimpleFragmentPageAdapter pageAdapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());
        pageAdapter.setTabTitles(tabTitles);
        pageAdapter.addFragment(CurrentWeatherView.newInstance());
        pageAdapter.addFragment(FiveDaysWeatherView.newInstance());

        weatherPager.setAdapter(pageAdapter);
        weatherTabs.setupWithViewPager(weatherPager);

        presenter.viewReady();
    }

    @Override
    public void showData() {

    }

    @Override
    public void showTemp(float temp) {
        Toast.makeText(this, "Temp is: " + temp, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.weather_refresh)
    void onRefreshClicked() {
        presenter.refreshClicked();
    }
}
