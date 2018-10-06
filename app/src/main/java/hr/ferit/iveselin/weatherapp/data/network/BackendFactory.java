package hr.ferit.iveselin.weatherapp.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendFactory {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static Retrofit retrofit;
    private static WeatherEndpoint weatherEndpoint;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static WeatherEndpoint getWeatherEndpoint() {
        if (weatherEndpoint == null) {
            weatherEndpoint = getRetrofitInstance().create(WeatherEndpoint.class);
        }
        return weatherEndpoint;
    }
}
