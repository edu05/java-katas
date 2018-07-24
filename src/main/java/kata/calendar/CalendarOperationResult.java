package kata.calendar;

public class CalendarOperationResult {

    private final boolean success;
    private final String failureReason;


    private CalendarOperationResult(boolean success, String failureReason) {
        this.success = success;
        this.failureReason = failureReason;
    }

    public static CalendarOperationResult success() {
        return new CalendarOperationResult(true, null);
    }

    public static CalendarOperationResult failure(String failureReason) {
        return new CalendarOperationResult(false, failureReason);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public String getFailureReason() {
        return failureReason;
    }
}
