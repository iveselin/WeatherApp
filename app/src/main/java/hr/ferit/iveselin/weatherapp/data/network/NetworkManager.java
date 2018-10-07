package hr.ferit.iveselin.weatherapp.data.network;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import retrofit2.Call;

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
    public Call<WeatherResponse> getWeatherForCity(String city) {
        return apiEndpoint.getWeatherByCityName(city, API_KEY);
    }

    @Override
    public Call<ForecastResponse> getForecastForCity(String city) {
        return apiEndpoint.getForecastByCityName(city, API_KEY);
    }
}
