package kata.calendar;

import org.junit.Test;

import java.time.LocalDateTime;

public class MeetingTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConstructMeetingsWithEndingBeforeTheyStart() throws Exception {
        new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().minusMinutes(30));

    }
}