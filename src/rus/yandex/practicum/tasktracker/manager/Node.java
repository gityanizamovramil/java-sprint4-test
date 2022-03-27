package rus.yandex.practicum.tasktracker.manager;

public class Node<Task> {

    public Node<Task> next;
    public Task current;
    public Node<Task> prev;

    public Node(Node<Task> prev, Task current, Node<Task> next) {
        this.current = current;
        this.next = next;
        this.prev = prev;
    }

    public Node(Task current) {
        this.current = current;
        this.next = null;
        this.prev = null;
    }

    public Node() {

    }

}
