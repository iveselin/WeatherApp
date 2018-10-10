package hr.ferit.iveselin.weatherapp.ui.current_weather.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.data.network.NetworkManager;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;

public class CurrentWeatherPresenter implements CurrentWeatherInterface.Presenter, NetworkInterface.OnFinishedWeatherListener {

    private CurrentWeatherInterface.View view;

    private NetworkInterface networkInterface;
    protected LatLng currentLocation;

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
        if (currentLocation != null) {
            networkInterface.getWeatherForLocation(currentLocation, this);
        } else {
            view.showError();
        }
    }

    @Override
    public void onFinished(WeatherResponse data) {
        if (data != null) {
            view.showData(data);
        } else {
            view.showError();
        }
    }


    @Override
    public void onFailure(Throwable throwable) {
        view.showError();
    }
}
