package hr.ferit.iveselin.weatherapp.ui.current_weather.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;
import hr.ferit.iveselin.weatherapp.ui.current_weather.presentation.CurrentWeatherPresenter;

public class CurrentWeatherView extends Fragment implements CurrentWeatherInterface.View {

    public static CurrentWeatherView newInstance() {
        CurrentWeatherView currentWeatherView = new CurrentWeatherView();
        return currentWeatherView;
    }


    private CurrentWeatherInterface.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CurrentWeatherPresenter();
        presenter.setView(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUi();
    }

    private void setUi() {


        presenter.viewReady();
    }

    @Override
    public void showData() {

    }

    @OnClick(R.id.weather_refresh)
    void onRefreshClicked() {
        presenter.refreshClicked();
    }
}
