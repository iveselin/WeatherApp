package hr.ferit.iveselin.weatherapp.ui.map_screen.presentation;

import com.google.android.gms.maps.model.LatLng;

import hr.ferit.iveselin.weatherapp.ui.map_screen.MapScreenInterface;

public class MapPresenter implements MapScreenInterface.Presenter {

    private static final float DEFAULT_ZOOM = 15f;

    private MapScreenInterface.View view;

    private LatLng location;

    @Override
    public void setView(MapScreenInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        view.moveMapCamera(location, DEFAULT_ZOOM);
    }

    @Override
    public void startingLocation(LatLng latLng) {
        this.location = latLng;
    }

    @Override
    public void mapClicked(LatLng clickedLocation) {
        location = clickedLocation;
        view.showDialog();
    }

    @Override
    public void locationAccepted() {
        view.returnLocationResult(location.latitude, location.longitude);
    }

    @Override
    public void locationDeclined() {

    }
}
