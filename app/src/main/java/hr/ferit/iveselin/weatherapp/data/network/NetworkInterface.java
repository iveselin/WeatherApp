package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;

public interface NetworkInterface {
    Call<WeatherResponse> getWeatherForCity(String city);

    Call<ForecastResponse> getForecastForCity(String city);
}
