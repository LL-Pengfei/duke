public class Task {
    protected String description;
    protected boolean isDone;
    public static int size = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size++;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
//Task t = new Task("read book");
//t.markAsDone()