package kata.calendar;

import java.time.LocalDateTime;

public class Meeting {
    private final String name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Meeting(String name, LocalDateTime start, LocalDateTime end) {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Start time " + start + " is not before end time " + end);
        }

        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
