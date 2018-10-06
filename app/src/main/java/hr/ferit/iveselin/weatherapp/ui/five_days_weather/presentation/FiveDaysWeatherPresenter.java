package hr.ferit.iveselin.weatherapp.ui.five_days_weather.presentation;

import hr.ferit.iveselin.weatherapp.ui.five_days_weather.FiveDaysWeatherInterface;

public class FiveDaysWeatherPresenter implements FiveDaysWeatherInterface.Presenter {

    private FiveDaysWeatherInterface.View view;

    @Override
    public void setView(FiveDaysWeatherInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {

    }
}
