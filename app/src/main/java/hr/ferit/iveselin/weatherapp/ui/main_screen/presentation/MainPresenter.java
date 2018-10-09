package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;


public class MainPresenter implements MainScreenInterface.Presenter {

    private static final String TAG = "MainPresenter";

    private static final LatLng DEFAULT_LOCATION_OSIJEK = new LatLng(45.554962, 18.695514);

    private MainScreenInterface.View view;

    private boolean locationPermissionGranted = false;
    private LatLng currentLocation;

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
        if (searchCityString.isEmpty()) {
            view.showEmptyInputError();
            return;
        }
        view.getLocationFromAddress(searchCityString);
    }

    @Override
    public void mapPressed() {
        if (locationPermissionGranted) {
            view.showMap(currentLocation);
        } else {
            view.showMap(DEFAULT_LOCATION_OSIJEK);
        }
    }

    @Override
    public void locationPermissionGranted(boolean isGranted) {
        this.locationPermissionGranted = isGranted;
        getLocationWeather();
    }

    @Override
    public void currentLocation(double latitude, double longitude) {
        this.currentLocation = new LatLng(latitude, longitude);
        view.setLocation(currentLocation);
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

    @Override
    public void receivedLocationFromMap(double latitude, double longitude) {
        currentLocation(latitude, longitude);
    }
}
