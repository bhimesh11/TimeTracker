import Data.Category;
import Data.CurrentTasks;
import Data.Task;
import Logger.logger;
import utils.Args;
import utils.ArgsUtil;
import utils.Command;

public class timeTracker
{
    public static void main(String[] args) {

        ArgsUtil argUtil = new ArgsUtil();
     Args Arguments =   argUtil.parseArgs(args);

     CurrentTasks currentTasks = new CurrentTasks();
     currentTasks.addTask(new Task("coding",new Category("productivity")));


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

     }
    }
}
