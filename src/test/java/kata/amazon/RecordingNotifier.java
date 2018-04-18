package kata.amazon;

public class RecordingNotifier extends Notifier {

    private String recordedNotifiedMessage;

    @Override
    public void notify(String message) {
        this.recordedNotifiedMessage = message;
    }

    public String getRecordedNotifiedMessage() {
        return recordedNotifiedMessage;
    }
}
