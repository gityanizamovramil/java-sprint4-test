package rus.yandex.practicum.tasktracker.model;

import rus.yandex.practicum.tasktracker.manager.Status;

import java.util.Objects;

public class Task {
    public String name;
    public String details;
    public Status status = Status.NEW;
    public Integer id;
    public Task() {

    }

    @Override
    public String toString() {
        return "rus.yandex.practicum.tasktracker.model.Task{"+"name='"+name+ '\''+
                ", details='"+ details +'\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                "}";
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return Objects.equals(name, otherTask.name) &&
               Objects.equals(details,otherTask.details) &&
               Objects.equals(status,otherTask.status) &&
               Objects.equals(id,otherTask.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;

        if(id != null) {
            hash = hash + id.hashCode();
        }
        hash = hash * 31;
        return  hash;
    }
}