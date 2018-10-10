package hr.ferit.iveselin.weatherapp.ui.main_screen.presentation;


import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import hr.ferit.iveselin.weatherapp.ui.main_screen.MainScreenInterface;

@RunWith(MockitoJUnitRunner.class)
public class MainScreenPresenterTest {


    @Mock
    MainScreenInterface.View view;

    MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter();

        presenter.setView(view);
    }

    @Test
    public void testViewReady() throws Exception {
        presenter.viewReady();

        verify(view).checkLocationPermission();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testSearchPressedNullStringShouldShowError() throws Exception {
        presenter.searchPressed(null);

        verify(view).showEmptyInputError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testSearchPressedEmptyStringShouldShowError() throws Exception {
        presenter.searchPressed("");

        verify(view).showEmptyInputError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testSearchPressedOkStringShouldGetLocationFromAddress() throws Exception {
        presenter.searchPressed("Osijek");

        verify(view).getLocationFromAddress("Osijek");
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testMapPressedCurrentLocationSetShouldShowMapWithCurrentLocation() throws Exception {
        presenter.currentLocation = new LatLng(45.815399, 15.966568);
        presenter.mapPressed();

        verify(view).showMap(new LatLng(45.815399, 15.966568));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testMapPressedCurrentLocationNullShouldShowMapWithDefaultLocation() throws Exception {
        presenter.mapPressed();

        verify(view).showMap(presenter.DEFAULT_LOCATION_OSIJEK);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testLocationPermissionGrantedTrueShouldSetIsGrantedAndGetLocationWeather() throws Exception {
        presenter.locationPermissionGranted(true);

        assertTrue(presenter.locationPermissionGranted);
        verify(view).findCurrentLocation();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void locationPermissionGrantedFalseShouldSetIsGrantedAndSetDefaultLocation() throws Exception {
        presenter.locationPermissionGranted(false);

        assertFalse(presenter.locationPermissionGranted);
        verify(view).setLocation(presenter.DEFAULT_LOCATION_OSIJEK);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testCurrentLocationFoundShouldSetCurrentLocation() throws Exception {
        presenter.currentLocationFound(45.815399, 15.966568);

        assertEquals(presenter.currentLocation, new LatLng(45.815399, 15.966568));
        verify(view).setLocation(new LatLng(45.815399, 15.966568));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testCurrentLocationNotFoundShouldSetDefaultLocation() throws Exception {
        presenter.currentLocationNotFound();

        verify(view).setLocation(presenter.DEFAULT_LOCATION_OSIJEK);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testReceivedLocationFromMapShouldCallCurrentLocationFound() throws Exception {
        presenter = Mockito.spy(new MainPresenter());
        presenter.setView(view);
        presenter.receivedLocationFromMap(45.815399, 15.966568);

        verify(presenter).currentLocationFound(45.815399, 15.966568);
    }


}
