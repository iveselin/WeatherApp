package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("weather")
    Call<WeatherResponse> getWeatherByCityName(@Query("q") String cityName,
                                               @Query("APPID") String apiKey);

    @GET("forecast")
    Call<ForecastResponse> getForecastByCityName(@Query("q") String cityName,
                                                 @Query("APPID") String apiKey);
}
