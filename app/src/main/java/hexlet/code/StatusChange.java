package hexlet.code;

public class StatusChange {
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String status;
    private Object oldValue;
    private Object newValue;

    public StatusChange(String status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public StatusChange(String status, Object value) {
        this.status = status;
        if (status.equals(ADDED)) {
            this.newValue = value;
        } else {
            this.oldValue = value;
        }
    }

    public String getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
