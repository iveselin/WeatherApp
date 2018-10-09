package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;


public class MainPresenter implements MainScreenInterface.Presenter {

    private static final String TAG = "MainPresenter";

    protected static final LatLng DEFAULT_LOCATION_OSIJEK = new LatLng(45.554962, 18.695514);

    protected MainScreenInterface.View view;

    protected boolean locationPermissionGranted = false;
    protected LatLng currentLocation;

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
        if (searchCityString == null || searchCityString.isEmpty()) {
            view.showEmptyInputError();
            return;
        }
        view.getLocationFromAddress(searchCityString);
    }

    @Override
    public void mapPressed() {
        if (currentLocation == null) {
            view.showMap(DEFAULT_LOCATION_OSIJEK);
        } else {
            view.showMap(currentLocation);
        }
    }

    @Override
    public void locationPermissionGranted(boolean isGranted) {
        this.locationPermissionGranted = isGranted;
        getLocationWeather();
    }

    @Override
    public void currentLocationFound(double latitude, double longitude) {
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
        currentLocationFound(latitude, longitude);
    }
}
