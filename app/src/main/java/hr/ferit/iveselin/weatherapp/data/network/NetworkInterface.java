package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;

public interface NetworkInterface {
    Call<WeatherResponse> getWeatherFromCity(String city);

    // TODO: 5.10.2018. forecast method
}
