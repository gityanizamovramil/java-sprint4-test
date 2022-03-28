package rus.yandex.practicum.tasktracker.model;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    public Map<Integer, Task> allTasks = new HashMap<>();
    public Map<Integer, Epic> allEpics = new HashMap<>();
    public Map<Integer, SubTask> allSubTasks = new HashMap<>();

    void addTask(Task task) {
        allTasks.put(task.getId(), task);
    }

    @Override
    public String toString() {
        return allTasks.toString() + allSubTasks.toString() + allEpics.toString();
    }
}