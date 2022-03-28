package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;
import rus.yandex.practicum.tasktracker.model.Epic;
import rus.yandex.practicum.tasktracker.model.SubTask;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    List<Task> history();

    void createTask(String name, String details, Status status, Integer id);

    void createSubTask(String name, String details, Status status, Integer id, Integer idEpic);

    void createEpic(String name, String details, Integer id);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<SubTask> getSubTasks();

    Task getTaskById(Integer i);

    Epic getEpicById(Integer i);

    SubTask getSubTaskById(Integer i);

    List<SubTask> getSubTasksInEpic(Integer idEpic);

    void eraseTaskById(Integer i);

    void eraseEpicById(Integer i);

    void eraseSubTaskById(Integer i);

    void eraseTasks();

    void eraseEpics();

    void eraseSubTasks();

    void checkStatusOfEpic(Integer id);

    void updateStatusOfEpics();
}
