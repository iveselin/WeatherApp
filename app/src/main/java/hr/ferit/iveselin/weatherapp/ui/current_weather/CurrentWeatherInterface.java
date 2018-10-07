package hr.ferit.iveselin.weatherapp.ui.current_weather;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;

public interface CurrentWeatherInterface {

    interface View {
        void showData(WeatherResponse data);

        void showError();
    }

    interface Presenter {
        void setView(View view);

        void refreshClicked();

        void viewReady(String city);
    }
}
