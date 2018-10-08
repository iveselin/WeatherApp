package hr.ferit.iveselin.weatherapp.ui.five_days_weather;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;

public interface FiveDaysWeatherInterface {

    interface View {
        void showData(List<WeatherResponse> data);

        void showError();
    }

    interface Presenter {

        void setView(View view);

        void viewReady();

        void locationChanged(LatLng newLocation);
    }
}
