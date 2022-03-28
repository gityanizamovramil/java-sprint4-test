package rus.yandex.practicum.tasktracker.manager;

import rus.yandex.practicum.tasktracker.model.Task;

public class Node {

    public Node next;
    public Task current;
    public Node prev;

    public Node(Node prev, Task current, Node next) {
        this.current = current;
        this.next = next;
        this.prev = prev;
    }

    public Node(Task current) {
        this.current = current;
        this.next = null;
        this.prev = null;
    }



}
