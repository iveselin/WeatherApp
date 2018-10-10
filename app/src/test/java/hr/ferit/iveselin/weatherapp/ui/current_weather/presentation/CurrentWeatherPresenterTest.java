package hr.ferit.iveselin.weatherapp.ui.current_weather.presentation;


import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.ui.current_weather.CurrentWeatherInterface;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherPresenterTest {

    @Mock
    NetworkInterface networkInterface;

    @Mock
    CurrentWeatherInterface.View view;

    CurrentWeatherPresenter presenter;

    @Before
    public void setUp() {
        presenter = new CurrentWeatherPresenter(networkInterface);
        presenter.setView(view);
    }


    @Test
    public void testLocationChangedShouldGetWeatherForLocation() throws Exception {
        presenter.locationChanged(new LatLng(45.815399, 15.966568));

        assertEquals(presenter.currentLocation, new LatLng(45.815399, 15.966568));
        verify(networkInterface).getWeatherForLocation(new LatLng(45.815399, 15.966568), presenter);
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testRefreshClickedShouldGetWeatherForLocation() throws Exception {
        presenter.currentLocation = new LatLng(45.815399, 15.966568);
        presenter.refreshClicked();

        verify(networkInterface).getWeatherForLocation(new LatLng(45.815399, 15.966568), presenter);
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFinishedDataNullShouldShowError() throws Exception {
        WeatherResponse data = null;

        presenter.onFinished(data);

        verify(view).showError();
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFinishedDataExistsShoulShowData() throws Exception {
        WeatherResponse data = new WeatherResponse();

        presenter.onFinished(data);

        verify(view).showData(data);
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFailureShouldShowError() throws Exception {
        presenter.onFailure(new Throwable());

        verify(view).showError();
        verifyNoMoreInteractions(view, networkInterface);
    }


}
