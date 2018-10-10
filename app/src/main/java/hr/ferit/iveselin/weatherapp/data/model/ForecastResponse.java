package hr.ferit.iveselin.weatherapp.data.model;

import java.util.List;

public class ForecastResponse {

    private List<WeatherResponse> list;

    public List<WeatherResponse> getList() {
        return list;
    }

    public void setList(List<WeatherResponse> list) {
        this.list = list;
    }
}
