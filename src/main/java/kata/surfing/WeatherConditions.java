package kata.surfing;

public class WeatherConditions {

    private final int temperatureInCelsius;
    private final int windSpeedInMetersPerHour;

    public WeatherConditions(int temperatureInCelsius, int windSpeedInMetersPerHour) {
        this.temperatureInCelsius = temperatureInCelsius;
        this.windSpeedInMetersPerHour = windSpeedInMetersPerHour;
    }

    public int getTemperatureInCelsius() {
        return temperatureInCelsius;
    }

    public int getWindSpeedInMetersPerHour() {
        return windSpeedInMetersPerHour;
    }
}
