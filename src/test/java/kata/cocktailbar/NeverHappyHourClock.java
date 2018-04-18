package kata.cocktailbar;

import java.time.LocalTime;

public class NeverHappyHourClock extends Clock {

    @Override
    public LocalTime getTime() {
        return LocalTime.of(13, 00);
    }
}
