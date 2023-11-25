package Data;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task
{
    public String TaskName;
    public Category category;

    public LocalDateTime startTime;
    public LocalDateTime endTime;

    private TaskStatus status;



    public Task(String taskName, Category category) {
        TaskName = taskName;
        this.category = category;
        this.startTime = LocalDateTime.now();
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task()
    {

    }

    public Task(String taskTime, Category category, LocalDateTime startTime, LocalDateTime endTime, TaskStatus status) {
        TaskName = taskTime;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "TaskName='" + TaskName + '\'' +
                ", category=" + category.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }


    public String getCsvFormat() {

        return TaskName + "," +
                category.getName() + "," +
                startTime + "," +
                endTime + "," +
                status;
    }

    public Duration getTaskDuration()
    {
        if(this.getEndTime()==null)
        {
            return null;
        }
        return Duration.between(this.getStartTime(),this.getEndTime());
    }
}
