package hr.ferit.iveselin.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private List<Weather> weather;
    private Main main;
    private Rain rain;
    private int dt;

    public Main getMain() {
        return main;
    }

    public Rain getRain() {
        return rain;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public int getDt() {
        return dt;
    }

    public class Main {
        private float temp;
        private float pressure;
        private int humidity;
        private float temp_min;
        private float temp_max;

        public int getHumidity() {
            return humidity;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public float getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }
    }

    public class Weather {

        private String description;
        @SerializedName("icon")
        private String iconId;

        public String getDescription() {
            return description;
        }

        public String getIconId() {
            return iconId;
        }
    }

    public class Rain {

        @SerializedName("3h")
        private int mmOfRain;

        public int getMmOfRain() {
            return mmOfRain;
        }
    }
}
