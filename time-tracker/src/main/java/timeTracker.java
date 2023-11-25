import Data.Category;
import Data.CurrentTasks;
import Data.Task;
import Logger.logger;
import utils.Args;
import utils.ArgsUtil;
import utils.Command;
import utils.fileUtil;

import javax.crypto.Cipher;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Map;

public class timeTracker
{
    public static void main(String[] args) throws URISyntaxException, IOException {

        ArgsUtil argUtil = new ArgsUtil();
     Args Arguments =   argUtil.parseArgs(args);
    // currentTasks.addTask(new Task("coding",new Category("productivity")));

     fileUtil fileutil = new fileUtil();
     CurrentTasks currentTasks = fileutil.getSavedTasks();



switch (Arguments.getCommand())
     {
         case TASK_START -> {

             Task task = new Task(Arguments.getTaskName(), new Category(Arguments.getCategoryName()));
             currentTasks.addTask(task);

         }
         case TASK_STOP ->
         {
             currentTasks.completeTask(Arguments.getTaskName());
         }
         case REPORT_TASKS -> {
             Map<String, Duration> taskReport = currentTasks.getTaskReport();
             for(Map.Entry<String,Duration> entry : taskReport.entrySet())
             {
                 System.out.println("Task: " + entry.getKey());
                 System.out.println("Duration in minutes: " + entry.getValue().toMinutes());
                 }

         }
         case REPORT_CATEGORIES -> {
             Map<String,Duration> categoryReport = currentTasks.getCategoryReport();
             for(Map.Entry<String,Duration> entry:categoryReport.entrySet())
             {
                 System.out.println("Category: " + entry.getKey());
                 System.out.println("Duration in minutes: " + entry.getValue().toMinutes());
             }
         }

     }
     fileutil.saveTasksForFile(currentTasks);
    }
}
