package kata.surfing;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SurfingAppTest {

    private WeatherApp weatherApp = mock(WeatherApp.class);
    private NotifierApp notifierApp = mock(NotifierApp.class);
    private ClockApp clockApp = mock(ClockApp.class);
    private SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp);

    @Before
    public void setUp() throws Exception {
        when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 00)));
    }

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

    @Test
    public void shouldNotifyAndVibrateToGoSurfingASAPOnFantasticWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(26, 13));
        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notifyAndVibrate("Go surfing ASAP!");
    }

    @Test
    public void shouldNotDoAnythingWhenNotOClockTime() throws Exception {
        when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 8)));

        surfingApp.alertOnGoodWeatherConditions();

        verifyZeroInteractions(weatherApp, notifierApp);
    }
}