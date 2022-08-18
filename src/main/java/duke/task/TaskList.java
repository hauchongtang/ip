package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;
    static String NO_SUCH_INDEX = "No such index in the list, please try again.";

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private String handleTaskOutput(Task task, int id) {
        return String.format("%d. %s", id, task.toString());
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    private String getItemsLeft() {
        if (taskList.isEmpty()) {
            return "List is empty";
        } else {
            return String.format("Now you have %d tasks in the list", taskList.size());
        }
    }

    public void removeTaskFromList(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }

            Task taskToRemove = this.taskList.get(id-1);
            this.taskList.remove(id-1);
            String taskRemovedOutput = String.format("Noted. I've removed this task:\n %s\n%s\n",
                    taskToRemove.toString(), getItemsLeft());
            System.out.println(taskRemovedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTaskToList(Task task) {
        try {
            this.taskList.add(task);
            String taskAddedOutput = String.format("Got it. I've added this task:\n  %s\n%s\n",
                    task.toString(), getItemsLeft());
            System.out.println(taskAddedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        String toPrint = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toConcat = handleTaskOutput(task, i+1);
            toPrint = String.format("%s\n%s", toPrint, toConcat);
        }

        System.out.println(toPrint.substring(1) + "\n");
    }

    public void markTaskAsDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id-1);
            targetTask.markAsDone(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void markTaskAsUnDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id-1);
            targetTask.markAsUnDone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}