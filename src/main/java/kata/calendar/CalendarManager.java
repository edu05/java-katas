package kata.calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalendarManager {

    private List<Meeting> meetings = new ArrayList<>();

    public CalendarOperationResult schedule(Meeting newMeeting) {
        Optional<CalendarOperationResult> failedCalendarOperationResult = meetings.stream()
                .map(meeting -> {
                    if (meeting.getName().equals(newMeeting.getName())) {
                        return CalendarOperationResult.failure("existing meeting with same name " + meeting);
                    } else if (!newMeeting.getEnd().isBefore(meeting.getStart()) && !newMeeting.getStart().isAfter(meeting.getEnd())) {
                        return CalendarOperationResult.failure("clashing with meeting " + meeting);
                    } else {
                        return CalendarOperationResult.success();
                    }
                })
                .filter(calendarOperationResult -> calendarOperationResult.isFailure())
                .findFirst();

        if (failedCalendarOperationResult.isPresent()) {
            return failedCalendarOperationResult.get();
        } else {
            meetings.add(newMeeting);
            return CalendarOperationResult.success();
        }
    }

    public List<Meeting> scheduledMeetings() {
        return meetings.stream()
                .sorted((lhs, rhs) -> lhs.getStart().isBefore(rhs.getStart()) ? -1 : 1)
                .collect(Collectors.toList());
    }
}
