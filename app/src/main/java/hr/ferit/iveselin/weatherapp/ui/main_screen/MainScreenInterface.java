package hr.ferit.iveselin.weatherapp.ui.main_screen;

import com.google.android.gms.maps.model.LatLng;

public interface MainScreenInterface {


    interface View {

        void showErrorMessage();

        void checkLocationPermission();

        void askLocationPermission();

        void showMap();

        void setLocation(LatLng latLng);

        void findCurrentLocation();

        void getLocationFromAddress(String searchCityString);
    }


    interface Presenter {

        void setView(View view);

        void viewReady();

        void searchPressed(String searchCityString);

        void mapPressed();

        void locationPermissionGranted(boolean isGranted);

        void currentLocation(double latitude, double longitude);

        void currentLocationNotFound();
    }
}
