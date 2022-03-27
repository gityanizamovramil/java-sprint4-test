import rus.yandex.practicum.tasktracker.manager.Managers;
import rus.yandex.practicum.tasktracker.manager.Status;
import rus.yandex.practicum.tasktracker.manager.TaskManager;
import rus.yandex.practicum.tasktracker.model.Epic;
import rus.yandex.practicum.tasktracker.model.SubTask;
import rus.yandex.practicum.tasktracker.model.Task;

public class Main {
    public static void main(String[] args) {

    TaskManager taskManager = Managers.getDefault();

        System.out.println("Начинаем 1-ый тест: произвольные условия");
        System.out.println(" ");
        System.out.println(" ");

    taskManager.createTask("1","1", Status.DONE, 1);
    taskManager.createTask("2","2", Status.NEW, 2);

        System.out.println("Распечатаем Tasks (No. 1 Done, No. 2 New)");
        System.out.println(taskManager.getTasks());

        System.out.println("Распечатаем историю просмотров");
        Task task1 = taskManager.getTaskById(1);
        Task task2 = taskManager.getTaskById(2);
        System.out.println(taskManager.history());
        System.out.println(" ");

    taskManager.createEpic("4", "4",4);
    taskManager.createSubTask("3","3", Status.NEW, 3, 4);
    taskManager.createSubTask("5","5", Status.IN_PROGRESS, 5, 4);

        System.out.println("Распечатаем Epics (No. 4 In progress)");
        System.out.println(taskManager.getEpics());
        System.out.println("Распечатаем SubTasks (No. 3 New, No. 5 In progress)");
        System.out.println(taskManager.getSubTasks());

        System.out.println("Распечатаем историю просмотров");
        SubTask subTask3 = taskManager.getSubTaskById(3);
        Epic epic4 = taskManager.getEpicById(4);
        SubTask subTask5 = taskManager.getSubTaskById(5);
        System.out.println(taskManager.history());
        System.out.println(" ");

    taskManager.createEpic("7", "7",7);
    taskManager.createSubTask("6","6", Status.DONE, 6, 7);

        System.out.println("Распечатаем Epics (No. 4 In progress, No. 7 Done)");
        System.out.println(taskManager.getEpics());
        System.out.println("Распечатаем SubTasks (No. 3 New, No. 5 In progress, No. 6 Done)");
        System.out.println(taskManager.getSubTasks());

        System.out.println("Распечатаем историю просмотров");
        SubTask subTask6 = taskManager.getSubTaskById(6);
        Epic epic7 = taskManager.getEpicById(7);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем все задачи");
        System.out.println(taskManager);
        System.out.println(" ");

        System.out.println("Обновляем статус SubTask No. 6 New и обновляем статус его Epic No. 7 New");

    SubTask subTask = new SubTask("6", "6", Status.NEW, 6, 7);
    
    taskManager.updateSubTask(subTask);

        System.out.println("Распечатаем все задачи");
        System.out.println(taskManager);

        System.out.println(" ");
        System.out.println("Удалим Task No. 1, Epic No 4 (у него есть SubTask No. 3, No. 5)");

    taskManager.eraseTaskById(1);
    taskManager.eraseEpicById(4);

        System.out.println("Распечатаем все задачи вместе");
        System.out.println("Распечатаем все Task, SubTask, Epic, все задачи вместе");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getSubTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager);
        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());

        System.out.println(" ");
        System.out.println("Забьем память истории элементами: 4 раза посмотрим SubTask No. 6");

        subTask6 = taskManager.getSubTaskById(6);
        subTask6 = taskManager.getSubTaskById(6);
        subTask6 = taskManager.getSubTaskById(6);
        subTask6 = taskManager.getSubTaskById(6);

        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());

        System.out.println("Забьем память истории элементами: 4 раза посмотрим Task No. 2");

        task2 = taskManager.getTaskById(2);
        task2 = taskManager.getTaskById(2);
        task2 = taskManager.getTaskById(2);
        task2 = taskManager.getTaskById(2);

        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());
        System.out.println(" ");
        System.out.println("Распечатаем все задачи вместе");
        System.out.println(taskManager);

        System.out.println(" ");
        System.out.println("Удалим Task No. 2");

    taskManager.eraseTaskById(2);

        System.out.println("Распечатаем все задачи вместе");
        System.out.println(taskManager);
        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());

        System.out.println(" ");
        System.out.println("Удалим SubTask No. 6");

    taskManager.eraseSubTaskById(6);

        System.out.println("Распечатаем все задачи вместе");
        System.out.println(taskManager);
        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());

        System.out.println(" ");
        System.out.println("Удалим Epic No 7 (у него есть SubTask No. 6)");

    taskManager.eraseEpicById(7);

        System.out.println("Распечатаем все задачи вместе");
        System.out.println(taskManager);
        System.out.println("Распечатаем историю просмотров");
        System.out.println(taskManager.history());

        System.out.println("Конец произвольного тестирования");
        System.out.println(" ");

        System.out.println(" ");
        System.out.println("Начинаем тестирование согласно ТЗ № 4:");
        System.out.println(" ");
        System.out.println(" ");

    taskManager.createTask("1","1", Status.DONE, 1);
    taskManager.createTask("2","2", Status.NEW, 2);
    taskManager.createEpic("3", "3",3);
    taskManager.createSubTask("4","4", Status.NEW, 4, 3);
    taskManager.createSubTask("5","5", Status.IN_PROGRESS, 5, 3);
    taskManager.createSubTask("6","6", Status.IN_PROGRESS, 6, 3);
    taskManager.createEpic("7", "7",7);

        System.out.println("Распечатаем все задачи");
        System.out.println(taskManager);
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем все задачи по порядку id:");
        task1 = taskManager.getTaskById(1);
        task2 = taskManager.getTaskById(2);
        Task task3 = taskManager.getEpicById(3);
        Task task4 = taskManager.getSubTaskById(4);
        Task task5 = taskManager.getSubTaskById(5);
        Task task6 = taskManager.getSubTaskById(6);
        Task task7 = taskManager.getEpicById(7);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем задачу с id No. 4, два раза:");
        task4 = taskManager.getSubTaskById(4);
        task4 = taskManager.getSubTaskById(4);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем задачу с id No. 7, два раза:");
        task7 = taskManager.getEpicById(7);
        task7 = taskManager.getEpicById(7);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем задачу с id No. 5, два раза:");
        task5 = taskManager.getSubTaskById(5);
        task5 = taskManager.getSubTaskById(5);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем задачу с id No. 2, два раза:");
        task2 = taskManager.getTaskById(2);
        task2 = taskManager.getTaskById(2);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Распечатаем историю просмотров: вызываем задачу с id No. 1, два раза:");
        task1 = taskManager.getTaskById(1);
        task1 = taskManager.getTaskById(1);
        System.out.println(taskManager.history());
        System.out.println(" ");

        System.out.println("Удалим задачу с id No. 2");

    taskManager.eraseTaskById(2);

        System.out.println("Распечатаем все задачи");
        System.out.println(taskManager);
        System.out.println(" ");
        System.out.println("Распечатаем историю просмотров, задача с id No. 2 удалена:");
        System.out.println(taskManager.history());
        System.out.println(" ");
        System.out.println("Удалим эпик с id No. 3 (у него есть подзадачи с id No. 4, 5, 6 ):");

    taskManager.eraseEpicById(3);

        System.out.println("Распечатаем все задачи");
        System.out.println(taskManager);
        System.out.println(" ");
        System.out.println("Распечатаем историю просмотров, задачи с id No. 3, 4, 5, 6 удалены:");
        System.out.println(taskManager.history());
        System.out.println(" ");
        System.out.println("Конец тестирования согласно ТЗ № 4");
        System.out.println(" ");

    }
}