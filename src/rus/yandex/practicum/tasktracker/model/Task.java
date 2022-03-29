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

    public Task(String name, String details, Status status, Integer id) {

        this.details = details;
        this.status = status;
        this.id = id;
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


    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}