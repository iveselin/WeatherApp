package hr.ferit.iveselin.weatherapp.ui.main_screen;

public interface MainScreenInterface {


    interface View {

        void showTemp(float temp);

        void showErrorMessage();

        void showData();
    }


    interface Presenter {

        void setView(View view);

        void viewReady();

        void searchPressed(String searchCityString);
    }
}
