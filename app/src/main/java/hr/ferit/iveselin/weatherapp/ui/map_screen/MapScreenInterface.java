package hr.ferit.iveselin.weatherapp.ui.map_screen;

import com.google.android.gms.maps.model.LatLng;

public interface MapScreenInterface {

    interface View {

        void moveMapCamera(LatLng location, float zoom);

        void showDialog();

        void returnLocationResult(double latitude, double longitude);

    }

    interface Presenter {

        void setView(View view);

        void viewReady();

        void startingLocation(LatLng startingLocation);


        void mapClicked(LatLng clickedLocation);

        void locationAccepted();

        void locationDeclined();

    }
}
