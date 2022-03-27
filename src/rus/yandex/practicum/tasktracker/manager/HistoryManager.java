package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    void remove(Integer id);

    List<Task> getHistory();

}
