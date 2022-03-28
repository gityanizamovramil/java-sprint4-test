package rus.yandex.practicum.tasktracker.model;

import rus.yandex.practicum.tasktracker.manager.Status;

public class SubTask extends Task {
    public Integer idEpic;


    public SubTask(String name, String details, Status status, Integer id, Integer idEpic) {
        super(name, details, status, id);
        this.idEpic = idEpic;
    }

    @Override
    public String toString() {
        return "rus.yandex.practicum.tasktracker.model.SubTask{"+"name='"+name+ '\''+
                ", details='"+ details +'\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", idEpic='" + idEpic + '\'' +
                "}";
    }
}