package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;
import rus.yandex.practicum.tasktracker.model.Epic;
import rus.yandex.practicum.tasktracker.model.SubTask;
import rus.yandex.practicum.tasktracker.model.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    public Storage storage = new Storage();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public Task getTaskById(Integer i) {
        historyManager.add(storage.allTasks.get(i));
        return storage.allTasks.get(i);
    }

    @Override
    public Epic getEpicById(Integer i) {
        historyManager.add(storage.allEpics.get(i));
        return storage.allEpics.get(i);
    }

    @Override
    public SubTask getSubTaskById(Integer i) {
        historyManager.add(storage.allSubTasks.get(i));
        return storage.allSubTasks.get(i);
    }

    @Override
    public List<Task> history() {
        return historyManager.getHistory();
    }

    @Override
    public void createTask(String name, String details, Status status, Integer id) {
        Task task = new Task(name, details, status, id);

        storage.allTasks.put(id, task);
    }

    @Override
    public void createSubTask(String name, String details, Status status, Integer id, Integer idEpic) {
        SubTask subTask = new SubTask(name, details, status, id, idEpic);

        storage.allSubTasks.put(id, subTask);
        if (storage.allEpics.containsKey(idEpic)) {
            storage.allEpics.get(idEpic).subTasks.put(id, subTask);
        }
        checkStatusOfEpic(idEpic);
    }

    @Override
    public void createEpic(String name, String details, Integer id) {
        Map<Integer, SubTask> subTasks = new HashMap<>();
        for (Integer i : storage.allSubTasks.keySet()) {
            if (storage.allSubTasks.get(i).idEpic.equals(id)) {
                subTasks.put(i, storage.allSubTasks.get(i));
            }
        }
        Epic epic = new Epic(subTasks, name, details, null, id);

        storage.allEpics.put(id, epic);
        checkStatusOfEpic(id);
    }

    @Override
    public void updateTask(Task task) {
        storage.allTasks.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        storage.allEpics.put(epic.getId(), epic);
        for (Integer i : storage.allSubTasks.keySet()) {
            storage.allSubTasks.put(i, epic.subTasks.get(i));
        }
        checkStatusOfEpic(epic.getId());
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        storage.allSubTasks.put(subTask.getId(), subTask);
        storage.allEpics.get(subTask.idEpic).subTasks.put(subTask.getId(), subTask);
        checkStatusOfEpic(subTask.idEpic);
        updateStatusOfEpics();
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Integer i : storage.allTasks.keySet()) {
            tasks.add(storage.allTasks.get(i));
        }
        return tasks;
    }

    @Override
    public List<Epic> getEpics() {
        List<Epic> epics = new ArrayList<>();
        for (Integer i : storage.allEpics.keySet()) {
            epics.add(storage.allEpics.get(i));
        }
        return epics;
    }

    @Override
    public List<SubTask> getSubTasks() {
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer i : storage.allSubTasks.keySet()) {
            subTasks.add(storage.allSubTasks.get(i));
        }
        return subTasks;
    }

    @Override
    public List<SubTask> getSubTasksInEpic(Integer idEpic) {
        List<SubTask> subTasks = new ArrayList<>();
        for (SubTask subTask : storage.allEpics.get(idEpic).subTasks.values()) {
            subTasks.add(subTask);
        }
        return subTasks;
    }

    @Override
    public void eraseTaskById(Integer i) {
        storage.allTasks.remove(i);
        historyManager.remove(i);
    }

    @Override
    public void eraseEpicById(Integer i) {
        Epic epic = storage.allEpics.get(i);
        storage.allEpics.remove(i);
        historyManager.remove(i);
        List<Integer> numbers = new ArrayList<>();
        for (Integer j : epic.subTasks.keySet()) {
            numbers.add(j);
        }
        for (Integer number : numbers) {
            storage.allSubTasks.remove(number);
            historyManager.remove(number);
        }
    }

    @Override
    public void eraseSubTaskById(Integer i) {
        Integer j = storage.allSubTasks.get(i).idEpic;
        storage.allEpics.get(j).subTasks.remove(i);
        storage.allSubTasks.remove(i);
        historyManager.remove(i);
        checkStatusOfEpic(j);
    }

    @Override
    public void eraseTasks() {
        storage.allTasks.clear();
    }

    @Override
    public void eraseEpics() {
        storage.allEpics.clear();
    }

    @Override
    public void eraseSubTasks() {
        storage.allSubTasks.clear();
    }

    @Override
    public void checkStatusOfEpic(Integer id) {
        int counter = 0;
        Epic epic = storage.allEpics.get(id);
        if (epic == null)
            return;

        epic.status = Status.NEW;
        for (SubTask subTask : epic.subTasks.values()) {
            if (subTask.status.equals(Status.IN_PROGRESS)) {
                epic.status = Status.IN_PROGRESS;
            } else if (subTask.status.equals(Status.DONE)) {
                counter++;
            }
            if (counter == epic.subTasks.size()) {
                epic.status = Status.DONE;
            } else if (counter > 0 && counter < epic.subTasks.size()) {
                epic.status = Status.IN_PROGRESS;
            }
        }

    }

    @Override
    public void updateStatusOfEpics() {
        for (Epic epic : storage.allEpics.values()) {
            for (SubTask subTask : epic.subTasks.values())
                subTask.status = storage.allSubTasks.get(subTask.id).status;
        }
        for (Epic epic : storage.allEpics.values()) {
            int counter = 0;
            epic.status = Status.NEW;
            for (SubTask subTask : epic.subTasks.values()) {
                if (subTask.status.equals(Status.IN_PROGRESS)) {
                    epic.status = Status.IN_PROGRESS;
                } else if (subTask.status.equals(Status.DONE)) {
                    counter++;
                }
                if (counter == epic.subTasks.size()) {
                    epic.status = Status.DONE;
                } else if (counter > 0 && counter < epic.subTasks.size()) {
                    epic.status = Status.DONE;
                }
            }
        }
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}