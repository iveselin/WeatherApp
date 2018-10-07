package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;

public interface NetworkInterface {

    void getWeatherForCity(String city, OnFinishedWeatherListener listener);

    void getForecastForCity(String city, OnFinishedForecastListener listener);

    interface OnFinishedWeatherListener {
        void onFinished(WeatherResponse data);

        void onFailure(Throwable throwable);
    }

    interface OnFinishedForecastListener {
        void onFinished(ForecastResponse data);

        void onFailure(Throwable throwable);
    }
}
