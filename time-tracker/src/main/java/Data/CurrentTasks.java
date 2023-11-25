package Data;

import Logger.logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CurrentTasks
{
private Map<String,Task> currentTask = new HashMap<>();

    public CurrentTasks(Map<String, Task> currentTaskCons) {
        this.currentTask = currentTaskCons;
    }
public CurrentTasks()
{

}

    public void addTask(Task task)
    {
        logger.log(currentTask.toString());
//      if(currentTask.putIfAbsent(task.getTaskName(), task)!=null);
//        {
//            logger.log("Task Already Exists,Skipping");
//        }
        if(currentTask.get(task.getTaskName())==null)
        {
            currentTask.put(task.getTaskName() ,task);
            logger.log(currentTask.toString());

        }
        else {
            logger.log("Task Already Exists,Skipping");
        }


    }

public void completeTask(String taskname)
{
    for(Map.Entry<String,Task> entries : currentTask.entrySet())
    {
        System.out.print(entries.getKey() + "values " + entries.getValue());

    }

   Task ExistingTask = currentTask.get(taskname);

   if(ExistingTask==null)
   {
       logger.log("No Tasks Found");
   }
   else {
       ExistingTask.setEndTime(LocalDateTime.now());
       ExistingTask.setStatus(TaskStatus.COMPLETE);
       logger.log(ExistingTask.toString());
   }
}

    public Map<String, Task> getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Map<String, Task> currentTask) {
        this.currentTask = currentTask;
    }

    public Map<String, Duration> getTaskReport()
    {
        return currentTask
                .values()
                .stream()
                .filter(task -> task.getEndTime()!=null)
                .collect(Collectors.toMap(Task::getTaskName,Task::getTaskDuration));

    }

    public Map<String,Duration> getCategoryReport()
    {
        Map<String , Duration> categoryReport = new HashMap<>();

        currentTask.values().
                stream()
                .filter(task -> task.getEndTime()!=null)
                .forEach(task ->
                {
                    String category = task.getCategory().getName();
                    Duration categoryDuration = categoryReport.getOrDefault(category,Duration.ZERO);
                    categoryReport.put(category,categoryDuration.plus(task.getTaskDuration()));
                });
        return categoryReport;
    }
public Map<String,Task> getCurrentTasks()
{
    return currentTask;
}

public void setCurrentTasks(Map<String,Task> currentTask)
{
    this.currentTask = currentTask;
}

    @Override
    public String toString() {
        return "CurrentTasks{" +
                "currentTask=" + currentTask +
                '}';
    }
}
