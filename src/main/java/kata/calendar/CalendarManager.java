package kata.calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalendarManager {

    private List<Meeting> meetings = new ArrayList<>();

    public CalendarOperationResult schedule(Meeting newMeeting) {
        Optional<Meeting> firstClashingMeeting = meetings.stream()
                .filter(meeting -> !newMeeting.getEnd().isBefore(meeting.getStart()) && !newMeeting.getStart().isAfter(meeting.getEnd()))
                .findFirst();

        if (!firstClashingMeeting.isPresent()) {
            meetings.add(newMeeting);
            return CalendarOperationResult.success();
        } else {
            return CalendarOperationResult.failure("clashing with meeting " + firstClashingMeeting.get());
        }
    }

    public List<Meeting> scheduledMeetings() {
        return meetings.stream()
                .sorted((lhs, rhs) -> lhs.getStart().isBefore(rhs.getStart()) ? -1 : 1)
                .collect(Collectors.toList());
    }
}
