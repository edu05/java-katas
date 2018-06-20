package kata.surfing;

public class SurfingApp {

    private static final int WAIKIKI_CITY_ID = 55;
    private final WeatherApp weatherApp;
    private final NotifierApp notifierApp;
    private final ClockApp clockApp;

    public SurfingApp(WeatherApp weatherApp, NotifierApp notifierApp, ClockApp clockApp) {
        this.weatherApp = weatherApp;
        this.notifierApp = notifierApp;
        this.clockApp = clockApp;
    }

    public void alertOnGoodWeatherConditions() {
        if (clockApp.getCurrentTime().getMinute() != 0) {
            return;
        }

        WeatherConditions weatherConditions = weatherApp.getWeatherConditions(WAIKIKI_CITY_ID);

        int temperature = weatherConditions.getTemperatureInCelsius();
        int windSpeed = weatherConditions.getWindSpeedInMetersPerHour();
        if (temperature > 25 && windSpeed > 12) {
            notifierApp.notifyAndVibrate("Go surfing ASAP!");
        } else if ((temperature >= 21 && windSpeed >= 12) || (temperature >= 25 && windSpeed >= 9)) {
            notifierApp.notify("You should go surfing today");
        }
    }
}
