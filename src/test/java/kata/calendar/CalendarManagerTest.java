package kata.calendar;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalendarManagerTest {

    private CalendarManager calendarManager;

    @Before
    public void setUp() throws Exception {
        //empty out the calendar with each test run
        calendarManager = new CalendarManager();
    }

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

    @Test
    public void shouldNotScheduleMeetingOverlappingEndTime() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting meetingOverlappingEndTime = new Meeting("meetingOverlappingEndTime", LocalDateTime.now().plusMinutes(30), LocalDateTime.now().plusHours(3));

        calendarManager.schedule(meeting);
        calendarManager.schedule(meetingOverlappingEndTime);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(1));
        assertThat(scheduledMeetings.get(0), is(meeting));
    }

    @Test
    public void shouldNotScheduleMeetingOverlappingStartTime() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting meetingOverlappingStartTime = new Meeting("meetingOverlappingStartTime", LocalDateTime.now().minusMinutes(30), LocalDateTime.now().plusMinutes(1));

        calendarManager.schedule(meeting);
        calendarManager.schedule(meetingOverlappingStartTime);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(1));
        assertThat(scheduledMeetings.get(0), is(meeting));

    }

    @Test
    public void shouldNotScheduleMeetingOverlappingFromTheInside() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting meetingOverlappingFromTheInside = new Meeting("meetingOverlappingFromTheInside", LocalDateTime.now().plusMinutes(30), LocalDateTime.now().plusMinutes(31));

        calendarManager.schedule(meeting);
        calendarManager.schedule(meetingOverlappingFromTheInside);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(1));
        assertThat(scheduledMeetings.get(0), is(meeting));


    }

    @Test
    public void shouldNotScheduleFullyOverlappingMeeting() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting fullyOverlappingMeeting = new Meeting("fullyOverlappingMeeting", LocalDateTime.now().minusMinutes(30), LocalDateTime.now().plusHours(3));

        calendarManager.schedule(meeting);
        calendarManager.schedule(fullyOverlappingMeeting);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(1));
        assertThat(scheduledMeetings.get(0), is(meeting));

    }

    @Test
    public void shouldScheduleMeetingWhenEndTimeIsBeforeAnyStartTime() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting earlierMeeting = new Meeting("earlier meeting", LocalDateTime.now().minusMinutes(30), LocalDateTime.now().minusMinutes(10));

        calendarManager.schedule(meeting);
        calendarManager.schedule(earlierMeeting);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(2));
    }

    @Test
    public void shouldScheduleMeetingWhenStartTimeIsAfterAnyEndTime() throws Exception {
        Meeting meeting = new Meeting("meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Meeting laterMeeting = new Meeting("later meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));

        calendarManager.schedule(meeting);
        calendarManager.schedule(laterMeeting);

        List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

        assertThat(scheduledMeetings.size(), is(2));
    }
}