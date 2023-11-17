package Data;

public class Category {

    public static final String NONE = "no-category";

    private String name;
    private int totalTime;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, int totalTime) {
        this.name = name;
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
