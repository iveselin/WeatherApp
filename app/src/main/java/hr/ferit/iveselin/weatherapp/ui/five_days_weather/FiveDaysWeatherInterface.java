package hr.ferit.iveselin.weatherapp.ui.five_days_weather;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;

public interface FiveDaysWeatherInterface {

    interface View {
        void showData(ForecastResponse data);

        void showError();
    }

    interface Presenter {

        void setView(View view);

        void viewReady();

        void locationChanged(LatLng newLocation);
    }
}
