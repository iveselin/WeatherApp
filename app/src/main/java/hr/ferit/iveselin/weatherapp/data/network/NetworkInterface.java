package hr.ferit.iveselin.weatherapp.data.network;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;

public interface NetworkInterface {

    void getWeatherForLocation(LatLng location, OnFinishedWeatherListener listener);

    void getForecastForLocation(LatLng location, OnFinishedForecastListener listener);

    interface OnFinishedWeatherListener {
        void onFinished(WeatherResponse data);

        void onFailure(Throwable throwable);
    }

    interface OnFinishedForecastListener {
        void onFinished(ForecastResponse data);

        void onFailure(Throwable throwable);
    }
}
