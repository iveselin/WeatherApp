package hr.ferit.iveselin.weatherapp.ui.main_screen;

public interface MainScreenInterface {


    interface View {

        void showErrorMessage();

        void checkLocationPermission();

        void askLocationPermission();

        void showMap();
    }


    interface Presenter {

        void setView(View view);

        void viewReady();

        void searchPressed(String searchCityString);

        void mapPressed();

        void locationPermissionGranted(boolean isGranted);
    }
}
