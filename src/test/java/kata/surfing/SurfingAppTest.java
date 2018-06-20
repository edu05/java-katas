package kata.surfing;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SurfingAppTest {

    private WeatherApp weatherApp = mock(WeatherApp.class);
    private NotifierApp notifierApp = mock(NotifierApp.class);
    private SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp);

    @Test
    public void shouldNotifyToGoSurfingOnFirstSetIdealWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(21, 13));
        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notify("You should go surfing today");
    }

    @Test
    public void shouldNotifyToGoSurfingOnSecondSetIdealWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(26, 9));
        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notify("You should go surfing today");
    }

    @Test
    public void shouldNotNotifyToGoSurfingOnNonIdealWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(3, 3));
        surfingApp.alertOnGoodWeatherConditions();

        verifyZeroInteractions(notifierApp);
    }
}