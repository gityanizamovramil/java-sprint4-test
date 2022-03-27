package rus.yandex.practicum.tasktracker.model;

import java.util.Map;

public class Epic extends Task {
    public Map<Integer, SubTask> subTasks;
    public Epic(Map<Integer, SubTask> subTasks) {
        super();
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