package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> history = new ArrayList<>();

    private Node head;
    private Node tail;
    private int size = 0;
    private HashMap<Integer, Node> linkedHistory = new HashMap<>();

    public void linkLast(Task current) {
        if(size == 0) {
            head = new Node(current);
            tail = head;
            size = size + 1;
            linkedHistory.put(current.id,tail);
        } else  {
            final Node newNode = new Node(tail, current, null);
            tail.next = newNode;
            tail = newNode;
            size = size + 1;
            linkedHistory.put(current.id,tail);
        }
    }

    public List<Task> getTasks() {
        List<Task> linkedTasks = new ArrayList<>();
        Node currentTask = head;
        while (currentTask != null){
            linkedTasks.add(currentTask.current);
            currentTask = currentTask.next;
        }

        return linkedTasks;
    }

    public void removeNode(Node node) {
        if(node.next==null) {
            Integer oldTailId = tail.current.id;
            final Node prev = tail.prev;
                if(prev!=null) {
                         prev.next = null;
                }
            tail = prev;
            linkedHistory.remove(oldTailId);
            size = size - 1;
        } else if(node.prev==null) {
             Integer oldHeadId = head.current.id;
             final Node next = head.next;
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
