package hr.ferit.iveselin.weatherapp.ui.main_screen;

import com.google.android.gms.maps.model.LatLng;

public interface MainScreenInterface {


    interface View {

        void showErrorMessage();

        void showEmptyInputError();

        void checkLocationPermission();

        void askLocationPermission();

        void showMap(LatLng location);

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

        void receivedLocationFromMap(double latitude, double longitude);
    }
}
