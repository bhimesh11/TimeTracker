package Data;

import Logger.logger;

import java.time.LocalDateTime;
import java.util.*;

public class CurrentTasks
{
    private Map<String,Task> currentTask = new HashMap<>();


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
public void completeTask(String task)
{
   Task ExistingTask = currentTask.get(task);

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


}
