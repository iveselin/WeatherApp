package hr.ferit.iveselin.weatherapp.ui.five_days_weather.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.iveselin.weatherapp.R;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_date_time)
    TextView dateTime;
    @BindView(R.id.item_temperature)
    TextView temperature;
    @BindView(R.id.item_icon)
    ImageView icon;

    public ForecastViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
