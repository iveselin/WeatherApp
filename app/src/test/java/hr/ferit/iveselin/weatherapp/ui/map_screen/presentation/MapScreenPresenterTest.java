package hr.ferit.iveselin.weatherapp.ui.map_screen.presentation;


import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import hr.ferit.iveselin.weatherapp.ui.map_screen.MapScreenInterface;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class MapScreenPresenterTest {

    @Mock
    MapScreenInterface.View view;

    MapPresenter presenter;

    @Before
    public void setUp() {
        presenter = new MapPresenter();

        presenter.setView(view);
    }

    @Test
    public void testViewReadyShouldMoveCamera() throws Exception {
        presenter.location = new LatLng(45.815399, 15.966568);
        presenter.viewReady();

        verify(view).moveMapCamera(new LatLng(45.815399, 15.966568), presenter.DEFAULT_ZOOM);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testStartingLocationSetShouldSetLocation() {
        presenter.startingLocation(new LatLng(45.815399, 15.966568));

        assertEquals(presenter.location, new LatLng(45.815399, 15.966568));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testMapClickedShouldSetLocationShowDialog() throws Exception {
        presenter.mapClicked(new LatLng(45.815399, 15.966568));

        assertEquals(presenter.location, new LatLng(45.815399, 15.966568));
        verify(view).showDialog();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testLocationAcceptedShouldReturnResult() throws Exception {
        presenter.location = new LatLng(45.815399, 15.966568);
        presenter.locationAccepted();

        verify(view).returnLocationResult(45.815399, 15.966568);
        verifyNoMoreInteractions(view);
    }
}
