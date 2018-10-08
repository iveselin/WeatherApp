package hr.ferit.iveselin.weatherapp.ui.map_screen.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.ui.map_screen.MapScreenInterface;
import hr.ferit.iveselin.weatherapp.ui.map_screen.presentation.MapPresenter;

public class MapActivity extends AppCompatActivity implements MapScreenInterface.View, OnMapReadyCallback {

    private static final String TAG = "MapActivity";

    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_LATITUDE = "latitude";

    public static Intent getLaunchIntent(Context fromContext, double longitude, double latitude) {
        Intent launchIntent = new Intent(fromContext, MapActivity.class);
        launchIntent.putExtra(KEY_LONGITUDE, longitude);
        launchIntent.putExtra(KEY_LATITUDE, latitude);
        return launchIntent;
    }


    private MapPresenter presenter;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        presenter = new MapPresenter();
        presenter.setView(this);

        ButterKnife.bind(this);
        getExtras();
        setUi();
    }

    private void getExtras() {
        double longitude;
        double latitude;

        longitude = getIntent().getDoubleExtra(KEY_LONGITUDE, 0);
        latitude = getIntent().getDoubleExtra(KEY_LATITUDE, 0);

        presenter.startingLocation(new LatLng(longitude, latitude));
    }

    private void setUi() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                presenter.mapClicked(latLng);
            }
        });

        presenter.viewReady();
    }

    @Override
    public void moveMapCamera(LatLng location, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom));
    }

    @Override
    public void showDialog() {

    }
}
