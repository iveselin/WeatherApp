package hr.ferit.iveselin.weatherapp.ui.five_days_weather.presentation;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.ui.five_days_weather.FiveDaysWeatherInterface;

import static android.support.constraint.Constraints.TAG;

public class FiveDaysWeatherPresenter implements FiveDaysWeatherInterface.Presenter, NetworkInterface.OnFinishedForecastListener {

    private static final String TAG = "FiveDaysWeatherPresente";

    private FiveDaysWeatherInterface.View view;

    private NetworkInterface networkInterface;
    private LatLng currentLocation;

    public FiveDaysWeatherPresenter(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    @Override
    public void setView(FiveDaysWeatherInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {

    }

    @Override
    public void locationChanged(LatLng newLocation) {
        this.currentLocation = newLocation;

        fetchData();
    }

    private void fetchData() {
        networkInterface.getForecastForLocation(currentLocation, this);
    }

    @Override
    public void onFinished(ForecastResponse data) {
        Log.d(TAG, "onFinished: displaying data: " + data.getList().toString());
        view.showData(data.getList());
    }

    @Override
    public void onFailure(Throwable throwable) {
        view.showError();
    }
}
