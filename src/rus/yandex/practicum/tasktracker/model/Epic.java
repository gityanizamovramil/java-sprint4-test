package rus.yandex.practicum.tasktracker.model;

import rus.yandex.practicum.tasktracker.manager.Status;

import java.util.Map;

public class Epic extends Task {
    public Map<Integer, SubTask> subTasks;
    public Epic(Map<Integer, SubTask> subTasks,
                String name,
                String details,
                Status status,
                int id) {
        super(name, details, status, id);
        this.name = name;
        this.subTasks = subTasks;
    }

    @Override
    public String toString() {
        return "rus.yandex.practicum.tasktracker.model.Epic{"+"name='"+name+ '\''+
                ", details='"+ details +'\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\''+
                "}";
    }
}