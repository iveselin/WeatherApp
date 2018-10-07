package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManager implements NetworkInterface {

    private static final String API_KEY = "77401987f95b3e7a8bfa0ffe63807448";

    private static NetworkManager networkManager;

    private static ApiEndpoint apiEndpoint;


    private NetworkManager() {
        apiEndpoint = BackendFactory.getApiEndpoint();
    }

    public static NetworkInterface getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    @Override
    public void getWeatherForCity(String city, final OnFinishedWeatherListener listener) {
        apiEndpoint.getWeatherByCityName(city, API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                listener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void getForecastForCity(String city, final OnFinishedForecastListener listener) {
        apiEndpoint.getForecastByCityName(city, API_KEY).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                listener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
