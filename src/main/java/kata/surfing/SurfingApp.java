package kata.surfing;

public class SurfingApp {

    private final WeatherApp weatherApp;
    private final NotifierApp notifierApp;
    private final ClockApp clockApp;
    private final GPSApp gpsApp;

    public SurfingApp(WeatherApp weatherApp, NotifierApp notifierApp, ClockApp clockApp, GPSApp gpsApp) {
        this.weatherApp = weatherApp;
        this.notifierApp = notifierApp;
        this.clockApp = clockApp;
        this.gpsApp = gpsApp;
    }

    public void alertOnGoodWeatherConditions() {
        if (clockApp.getCurrentTime().getMinute() != 0) {
            return;
        }

        gpsApp.getCitiesNearby().stream()
                .filter(City::hasBeach)
                .forEach(city -> {
                    WeatherConditions weatherConditions = weatherApp.getWeatherConditions(city.getCityId());

                    int temperature = weatherConditions.getTemperatureInCelsius();
                    int windSpeed = weatherConditions.getWindSpeedInMetersPerHour();
                    if (temperature > 25 && windSpeed > 12) {
                        notifierApp.notifyAndVibrate("Go surfing ASAP to " + city.getCityName() + "!");
                    } else if ((temperature >= 21 && windSpeed >= 12) || (temperature >= 25 && windSpeed >= 9)) {
                        notifierApp.notify("You should go surfing today to " + city.getCityName());
                    }

                });
    }
}
