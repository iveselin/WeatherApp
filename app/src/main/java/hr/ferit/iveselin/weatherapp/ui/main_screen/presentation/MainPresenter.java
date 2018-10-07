package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;

import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.data.network.NetworkManager;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainScreenInterface.Presenter {

    private MainScreenInterface.View view;
    private NetworkInterface networkManager;
    private String city;

    public MainPresenter() {
        networkManager = NetworkManager.getInstance();
    }


    @Override
    public void setView(MainScreenInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        // TODO: 6.10.2018. check for location permissions and display weather for that city
        city = "Osijek";
        getData();
    }


    @Override
    public void searchPressed(String searchCityString) {
        city = searchCityString;
        getData();
    }

    private void getData() {
        networkManager.getWeatherForCity(city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                view.showTemp(response.body().getMain().getTemp());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                view.showErrorMessage();
            }
        });
    }
}
