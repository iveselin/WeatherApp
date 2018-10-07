package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;

import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.data.network.NetworkManager;
import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;


public class MainPresenter implements MainScreenInterface.Presenter {

    private MainScreenInterface.View view;
    private NetworkInterface networkManager;
    private String city;
    private boolean locationPermissionGranted = false;

    public MainPresenter() {
        networkManager = NetworkManager.getInstance();
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
        // TODO: 7.10.2018. extract city name or coordinates
        city = searchCityString;
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

    private void getLocationWeather() {
        if (locationPermissionGranted) {
            // TODO: 7.10.2018. get location, get location weather and display data
        } else {
            // TODO: 7.10.2018. get weather for default location - Osijek
        }
    }

}
