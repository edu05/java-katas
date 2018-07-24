package kata.calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarManager {

    private List<Meeting> meetings = new ArrayList<>();

    public void schedule(Meeting newMeeting) {
        boolean noOverlappingMeetings = meetings.stream()
                .allMatch(meeting -> newMeeting.getEnd().isBefore(meeting.getStart()) || newMeeting.getStart().isAfter(meeting.getEnd()));

        if (noOverlappingMeetings) {
            meetings.add(newMeeting);
        }
    }

    public List<Meeting> scheduledMeetings() {
        return meetings.stream()
                .sorted((lhs, rhs) -> lhs.getStart().isBefore(rhs.getStart()) ? -1 : 1)
                .collect(Collectors.toList());
    }
}
