package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> history = new ArrayList<>();

    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;
    private HashMap<Integer, Node<Task>> linkedHistory = new HashMap<>();

    public void linkLast(Task current) {
        if(size == 0) {
            head = new Node<>(current);
            tail = head;
            size = size + 1;
            linkedHistory.put(current.id,tail);
        } else if(size == 1) {
            final Node<Task> newNode = new Node<>(head, current, null);
            tail = newNode;
            head.next = newNode;
            size = size + 1;
            linkedHistory.put(current.id,tail);
        } else if(size > 1) {
            final Node<Task> oldTail = tail;
            final Node<Task> newNode = new Node<>(oldTail, current, null);
            tail = newNode;
            oldTail.next = newNode;
            size = size + 1;
            linkedHistory.put(current.id,tail);
        }
    }

    public List<Task> getTasks() {
        List<Task> linkedTasks = new ArrayList<>();
        Node<Task> currentTask = head;
        if(size >=1 ) {
            for (int i = 0; i < size(); i++) {
                Task task = new Task();
                task = currentTask.current;
                linkedTasks.add(task);
                currentTask = currentTask.next;
            }
        }
        return linkedTasks;
    }

    public void removeNode(Node<Task> node) {
        if(node.next==null) {
            Integer oldTailId = tail.current.id;
            final Node<Task> prev = tail.prev;
                if(prev!=null) {
                         prev.next = null;
                }
            tail = prev;
            linkedHistory.remove(oldTailId);
            size = size - 1;
        } else if(node.prev==null) {
             Integer oldHeadId = head.current.id;
             final Node<Task> next = head.next;
             next.prev = null;
             head = next;
             linkedHistory.remove(oldHeadId);
             size = size - 1;
        } else {
            Integer removedNodeId = node.current.id;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            linkedHistory.remove(removedNodeId);
            size = size - 1;
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public void add(Task task) {
        if(linkedHistory.containsKey(task.id)) {
            removeNode(linkedHistory.get(task.id));
        }
            linkLast(task);
    }

    @Override
    public void remove(Integer id) {
        if(linkedHistory.containsKey(id)) {
            removeNode(linkedHistory.get(id));
        }
    }

    @Override
    public List<Task> getHistory() {
        history = getTasks();
        return history;
    }
}
