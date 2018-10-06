package hr.ferit.iveselin.weatherapp.ui.current_weather;

public interface CurrentWeatherInterface {

    interface View {
        void showData();
    }

    interface Presenter {
        void setView(View view);

        void viewReady();
    }
}
