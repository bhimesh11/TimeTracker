package utils;

import Data.Category;
import Logger.logger;

public class ArgsUtil {

    public Args parseArgs(String[] args) {
        if (!validate(args)) {
            throw new RuntimeException("Invalid Arguments");

        }
        Args argsObj = new Args();
        String cmdString = args[0];
        String convertedCMDformat = cmdString.toLowerCase();
        Command command = switch (convertedCMDformat)
        {
            case "start" -> Command.TASK_START;
            case "stop" -> Command.TASK_STOP;
            case "report" -> "task".equals(args[1]) ? Command.REPORT_TASKS :
                    " ".equals(args[1]) ? Command.REPORT_CATEGORIES : null;
            default -> throw new RuntimeException("Invalid input arguments");

        };
        argsObj.setCommand(command);

        if (Command.TASK_START.equals(command) || Command.TASK_STOP.equals(command)) {
            argsObj.setTaskName(args[1]);
            argsObj.setCategoryName(args.length == 3 ? args[2] : Category.NONE);
        }
        return argsObj;
    }

    public boolean validate(String[] args)
    {
          if(args.length<2)
        {
            logger.log("Error! not enogh arguments");
            return false;
        }
        return true;
    }

}
