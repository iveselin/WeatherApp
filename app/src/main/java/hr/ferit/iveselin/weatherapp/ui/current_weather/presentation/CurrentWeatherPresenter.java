package hr.ferit.iveselin.weatherapp.ui.current_weather.presentation;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.data.network.NetworkManager;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;

public class CurrentWeatherPresenter implements CurrentWeatherInterface.Presenter, NetworkInterface.OnFinishedWeatherListener {

    private CurrentWeatherInterface.View view;

    private NetworkInterface networkInterface;
    private String city;

    public CurrentWeatherPresenter() {
        networkInterface = NetworkManager.getInstance();
    }


    @Override
    public void setView(CurrentWeatherInterface.View view) {
        this.view = view;
    }


    @Override
    public void viewReady(String city) {
        // TODO: 7.10.2018. move to different method
        this.city = city;

        networkInterface.getWeatherForCity(city, this);
    }

    @Override
    public void refreshClicked() {
        networkInterface.getWeatherForCity(city, this);
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
