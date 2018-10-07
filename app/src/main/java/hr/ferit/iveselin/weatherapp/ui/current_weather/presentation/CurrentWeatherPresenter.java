package hr.ferit.iveselin.weatherapp.ui.current_weather.presentation;

import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;

public class CurrentWeatherPresenter implements CurrentWeatherInterface.Presenter {

    private CurrentWeatherInterface.View view;


    @Override
    public void setView(CurrentWeatherInterface.View view) {
        this.view = view;
    }


    @Override
    public void viewReady() {
        view.showData();
    }

    @Override
    public void refreshClicked() {

    }
}
