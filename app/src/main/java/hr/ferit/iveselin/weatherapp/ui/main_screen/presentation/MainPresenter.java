package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;


public class MainPresenter implements MainScreenInterface.Presenter {

    private static final String TAG = "MainPresenter";

    public static final LatLng DEFAULT_LOCATION_OSIJEK = new LatLng(45.554962, 18.695514);

    private MainScreenInterface.View view;

    private boolean locationPermissionGranted = false;

    public MainPresenter() {

    }


    @Override
    public void setView(MainScreenInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        view.checkLocationPermission();
    }


    @Override
    public void searchPressed(String searchCityString) {
        view.getLocationFromAddress(searchCityString);
    }

    @Override
    public void mapPressed() {
        view.showMap();
    }

    @Override
    public void locationPermissionGranted(boolean isGranted) {
        this.locationPermissionGranted = isGranted;
        getLocationWeather();
    }

    @Override
    public void currentLocation(double latitude, double longitude) {
        view.setLocation(new LatLng(latitude, longitude));
    }

    @Override
    public void currentLocationNotFound() {
        view.setLocation(DEFAULT_LOCATION_OSIJEK);
    }


    private void getLocationWeather() {
        if (locationPermissionGranted) {
            view.findCurrentLocation();
        } else {
            view.setLocation(DEFAULT_LOCATION_OSIJEK);
        }
    }
}
