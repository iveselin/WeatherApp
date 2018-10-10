package hr.ferit.iveselin.weatherapp.ui.five_days_weather.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.TimeZone;

import hr.ferit.iveselin.weatherapp.R;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.util.ImageLoader;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private List<WeatherResponse> forecasts = new ArrayList<>();
    private Context context;

    public void setForecasts(List<WeatherResponse> forecasts) {
        this.forecasts.clear();
        this.forecasts = forecasts;
        notifyDataSetChanged();
    }

    public ForecastAdapter(Context fromContext) {
        this.context = fromContext;
    }


    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View forecastView = inflater.inflate(R.layout.item_forecast, parent, false);

        return new ForecastViewHolder(forecastView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        if (forecasts.isEmpty()) {
            return;
        }

        WeatherResponse forecast = forecasts.get(position);

        Calendar calendar = Calendar.getInstance();
        int offset = TimeZone.getDefault().getRawOffset();
        //unix timestamp is in seconds and its UTC
        calendar.setTimeInMillis(forecast.getDt() * 1000 + offset);
        String dateTime = calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR) +
                "\t" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        holder.temperature.setText(forecast.getMain().getTemp() + "°C");
        holder.dateTime.setText(dateTime);
        if (forecast.getRain() == null) {
            holder.rain.setText(R.string.no_rain_text);
        } else {
            holder.rain.setText(forecast.getRain().getMmOfRain() + "mm");
        }
        ImageLoader.loadImage(context, forecast.getWeather().get(0).getIconId(), holder.icon);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}
