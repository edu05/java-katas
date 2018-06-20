package kata.surfing;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SurfingAppTest {

    private WeatherApp weatherApp = mock(WeatherApp.class);
    private NotifierApp notifierApp = mock(NotifierApp.class);
    private ClockApp clockApp = mock(ClockApp.class);
    private GPSApp gpsApp =  mock(GPSApp.class);
    private SurfingApp surfingApp = new SurfingApp(weatherApp, notifierApp, clockApp, gpsApp);

    @Before
    public void setUp() throws Exception {
        when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 00)));
        when(gpsApp.getCitiesNearby()).thenReturn(Arrays.asList(new City(55, "Waikiki", true)));
    }

    @Test
    public void shouldNotifyToGoSurfingOnFirstSetIdealWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(21, 13));
        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notify("You should go surfing today to Waikiki");
    }

    @Test
    public void shouldNotifyToGoSurfingOnSecondSetIdealWeatherConditions() throws Exception {
        when(weatherApp.getWeatherConditions(55)).thenReturn(new WeatherConditions(26, 9));
        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notify("You should go surfing today to Waikiki");
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

        verify(notifierApp).notifyAndVibrate("Go surfing ASAP to Waikiki!");
    }

    @Test
    public void shouldNotDoAnythingWhenNotOClockTime() throws Exception {
        when(clockApp.getCurrentTime()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 8)));

        surfingApp.alertOnGoodWeatherConditions();

        verifyZeroInteractions(weatherApp, notifierApp);
    }

    @Test
    public void shouldCheckNearByCitiesForSurfingOpportunities() throws Exception {
        //ignore the fact that Madrid, Plymouth, London, Lagos and Hossegor are nowhere near each other...
        List<City> citiesNearBy = Arrays.asList(
                new City(1, "Madrid", false),
                new City(2, "Plymouth", true),
                new City(3, "London", false),
                new City(4, "Lagos", true),
                new City(5, "Hossegor", true));

        when(weatherApp.getWeatherConditions(2)).thenReturn(new WeatherConditions(30, 30));
        when(weatherApp.getWeatherConditions(4)).thenReturn(new WeatherConditions(0, 30));
        when(weatherApp.getWeatherConditions(5)).thenReturn(new WeatherConditions(27, 10));
        when(gpsApp.getCitiesNearby()).thenReturn(citiesNearBy);

        surfingApp.alertOnGoodWeatherConditions();

        verify(notifierApp).notifyAndVibrate("Go surfing ASAP to Plymouth!");
        verify(notifierApp).notify("You should go surfing today to Hossegor");
    }
}