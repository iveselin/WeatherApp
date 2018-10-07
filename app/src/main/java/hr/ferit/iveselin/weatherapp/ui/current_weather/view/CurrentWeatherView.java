package hr.ferit.iveselin.weatherapp.ui.current_weather.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;
import hr.ferit.iveselin.weatherapp.ui.current_weather.presentation.CurrentWeatherPresenter;

public class CurrentWeatherView extends Fragment implements CurrentWeatherInterface.View {

    private static final String TAG = "CurrentWeatherView";

    public static final String KEY_CITY = "city_to_show";

    public static CurrentWeatherView newInstance(String city) {
        Bundle args = new Bundle();
        args.putString(KEY_CITY, city);
        CurrentWeatherView currentWeatherView = new CurrentWeatherView();
        currentWeatherView.setArguments(args);
        return currentWeatherView;
    }

    @BindView(R.id.weather_location)
    TextView weatherCity;
    @BindView(R.id.weather_type)
    TextView weatherDescription;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.weather_temperature)
    TextView weatherTemperature;
    @BindView(R.id.weather_rain)
    TextView weatherRain;
    @BindView(R.id.weather_humidity)
    TextView weatherHumidity;
    @BindView(R.id.weather_pressure)
    TextView weatherPressure;


    private CurrentWeatherInterface.Presenter presenter;

    private String city;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        city = getArguments().getString(KEY_CITY);

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

        ButterKnife.bind(this, view);

        setUi();
    }

    private void setUi() {
        presenter.viewReady(city);
    }

    @Override
    public void showData(WeatherResponse data) {

        weatherCity.setText(city);
        weatherDescription.setText(data.getWeather().get(0).getDescription());
        weatherHumidity.setText(data.getMain().getHumidity() + "%");
        weatherTemperature.setText(data.getMain().getTemp() + "°C");
        if (data.getRain() != null) {
            weatherRain.setVisibility(View.VISIBLE);
            weatherRain.setText(data.getRain().getMmOfRain());
        } else {
            weatherRain.setVisibility(View.GONE);
        }
        weatherPressure.setText(Float.toString(data.getMain().getPressure()));
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.weather_refresh)
    void onRefreshClicked() {
        presenter.refreshClicked();
    }
}
