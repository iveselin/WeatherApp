package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("weather")
    Call<WeatherResponse> getWeatherByCityLocation(@Query("lat") double locationLatitude,
                                                   @Query("lon") double locationLongitude,
                                                   @Query("units") String units,
                                                   @Query("APPID") String apiKey);

    @GET("forecast")
    Call<ForecastResponse> getForecastByCityLocation(@Query("lat") double locationLatitude,
                                                     @Query("lon") double locationLongitude,
                                                     @Query("units") String units,
                                                     @Query("APPID") String apiKey);
}
