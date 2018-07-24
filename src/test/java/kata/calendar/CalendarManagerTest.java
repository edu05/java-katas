package kata.calendar;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalendarManagerTest {

    private CalendarManager calendarManager = new CalendarManager();

    @Test
    public void shouldScheduleMeetingsAndGetThemInOrder() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting anotherMeeting = new Meeting("another meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));

        calendarManager.schedule(anotherMeeting);
        calendarManager.schedule(meeting);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(2));
        assertThat(scheduledMeetings.get(0), is(meeting));
        assertThat(scheduledMeetings.get(1), is(anotherMeeting));
    }
}