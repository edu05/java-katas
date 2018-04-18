package kata.cocktailbar;

import java.time.LocalTime;

public class AlwaysHappyHourClock extends Clock {

    @Override
    public LocalTime getTime() {
        return LocalTime.of(21, 30);
    }
}
