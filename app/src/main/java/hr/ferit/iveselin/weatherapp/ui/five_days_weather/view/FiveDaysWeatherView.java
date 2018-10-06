package hr.ferit.iveselin.weatherapp.ui.five_days_weather.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.ui.five_days_weather.FiveDaysWeatherInterface;
import hr.ferit.iveselin.weatherapp.ui.five_days_weather.presentation.FiveDaysWeatherPresenter;

public class FiveDaysWeatherView extends Fragment implements FiveDaysWeatherInterface.View {

    public static FiveDaysWeatherView newInstance() {
        return new FiveDaysWeatherView();
    }

    private FiveDaysWeatherInterface.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new FiveDaysWeatherPresenter();
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_five_days_weather, container, false);
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
}
