package hr.ferit.iveselin.weatherapp.ui.five_days_weather;

public interface FiveDaysWeatherInterface {

    interface View {
        void showData();
    }

    interface Presenter {

        void setView(View view);

        void viewReady();
    }
}
