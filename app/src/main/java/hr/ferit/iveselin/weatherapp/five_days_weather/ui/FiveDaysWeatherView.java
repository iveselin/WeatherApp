package hr.ferit.iveselin.weatherapp.five_days_weather.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.ferit.iveselin.weatherapp.R;

public class FiveDaysWeatherView extends Fragment {

    public static FiveDaysWeatherView newInstance() {
        return new FiveDaysWeatherView();
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

    }
}
