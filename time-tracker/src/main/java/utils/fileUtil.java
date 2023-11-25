package utils;


import Data.Category;
import Data.CurrentTasks;
import Data.Task;
import Data.TaskStatus;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class fileUtil
{
    public static final String PATH = "task-info.csv";
  //  Path path = Paths.get(PATH);
    public CurrentTasks getSavedTasks() throws URISyntaxException, IOException {


       Path path = Paths.get(PATH);

        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        Map<String, Task> taskMap =
                Files.lines(path)
                        .map(line -> line.split(","))
                        .filter(tokenArray -> tokenArray.length == 5)
                        .map(tokenArray -> new Task(
                                tokenArray[0],
                                new Category(tokenArray[1]),
                                tokenArray[2] == null || "null".equals(tokenArray[2]) || tokenArray[2].isBlank() ? null : LocalDateTime.parse(tokenArray[2]),
                                tokenArray[3] == null || "null".equals(tokenArray[3]) || tokenArray[3].isBlank() ? null : LocalDateTime.parse(tokenArray[3]),
                                TaskStatus.valueOf(tokenArray[4])
                        ))
                        .collect(Collectors.toMap(Task::getTaskName, Function.identity()));

        return new CurrentTasks(taskMap);
    }

    public void saveTasksForFile(CurrentTasks task) throws IOException {

        Path filePath = Paths.get(PATH);
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
        List<String> lines = task.getCurrentTask()
                .values()
                .stream()
                .map(Task::getCsvFormat)
                .toList();
        Files.write(filePath,lines);

    }



}
