package hr.ferit.iveselin.weatherapp.ui.five_days_weather.presentation;


import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import hr.ferit.iveselin.weatherapp.data.model.ForecastResponse;
import hr.ferit.iveselin.weatherapp.data.model.WeatherResponse;
import hr.ferit.iveselin.weatherapp.data.network.NetworkInterface;
import hr.ferit.iveselin.weatherapp.ui.five_days_weather.FiveDaysWeatherInterface;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class FiveDaysWeatherPresenterTest {

    @Mock
    NetworkInterface networkInterface;

    @Mock
    FiveDaysWeatherInterface.View view;

    FiveDaysWeatherPresenter presenter;

    @Before
    public void setUp() {
        presenter = new FiveDaysWeatherPresenter(networkInterface);
        presenter.setView(view);

    }

    @Test
    public void testLocationChangedShouldFetchLocationForecast() throws Exception {
        presenter.locationChanged(new LatLng(45.815399, 15.966568));

        verify(networkInterface).getForecastForLocation(new LatLng(45.815399, 15.966568), presenter);
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFinishedDataNullShouldShowError() throws Exception {
        ForecastResponse data = new ForecastResponse();
        data.setList(null);
        presenter.onFinished(data);

        verify(view).showError();
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFinishedDataExistsShouldShowData() throws Exception {
        ForecastResponse data = new ForecastResponse();
        data.setList(new ArrayList<WeatherResponse>());
        presenter.onFinished(data);

        verify(view).showData(data.getList());
        verifyNoMoreInteractions(view, networkInterface);
    }

    @Test
    public void testOnFailureShouldShowError() throws Exception {
        presenter.onFailure(new Throwable());

        verify(view).showError();
        verifyNoMoreInteractions(view, networkInterface);
    }

}
