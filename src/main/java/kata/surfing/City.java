package kata.surfing;

public class City {

    private final int cityId;
    private final String cityName;
    private final boolean hasBeach;

    public City(int cityId, String cityName, boolean hasBeach) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.hasBeach = hasBeach;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public boolean hasBeach() {
        return hasBeach;
    }
}
