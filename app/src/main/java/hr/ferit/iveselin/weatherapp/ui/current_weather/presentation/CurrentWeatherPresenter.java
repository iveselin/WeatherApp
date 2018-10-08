package hr.ferit.iveselin.weatherapp.ui.current_weather.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.data.network.NetworkManager;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;

public class CurrentWeatherPresenter implements CurrentWeatherInterface.Presenter, NetworkInterface.OnFinishedWeatherListener {

    private CurrentWeatherInterface.View view;

    private NetworkInterface networkInterface;
    private LatLng currentLocation;

    public CurrentWeatherPresenter(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }


    @Override
    public void setView(CurrentWeatherInterface.View view) {
        this.view = view;
    }


    @Override
    public void viewReady() {

    }

    @Override
    public void locationChanged(LatLng location) {
        this.currentLocation = location;
        refreshClicked();
    }

    @Override
    public void refreshClicked() {
        networkInterface.getWeatherForLocation(currentLocation, this);
    }

    @Override
    public void onFinished(WeatherResponse data) {
        view.showData(data);
    }

    @Override
    public void onFailure(Throwable throwable) {
        view.showError();
    }
}