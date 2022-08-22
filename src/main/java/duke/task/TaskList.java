package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

public class TaskList {
    private static final String NO_SUCH_INDEX = "No such index in the list, please try again.";
    private static final String NO_TASKS_LEFT = "List is empty, 0 items left !";
    private final List<Task> taskList;


    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private String handleTaskOutput(Task task, int id) {
        return String.format("%d. %s", id, task.toString());
    }

    /**
     * Get a list of tasks
     * @return a task list
     * @see Task
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    private String getItemsLeft() {
        if (taskList.isEmpty()) {
            return NO_TASKS_LEFT;
        } else {
            return String.format("Now you have %d tasks in the list", taskList.size());
        }
    }

    /**
     * Retuns an instance of TaskList which contains search result from the given query string
     * @param query is the target search term for task description in the list.
     * @return a tasklist with the filtered result
     */
    public TaskList findMatchingTasks(String query) {
        List<Task> result = this.taskList.stream().filter(item -> item.getDescription().contains(query))
                .collect(Collectors.toList());
        TaskList filteredTasks = new TaskList(result);

        return filteredTasks;
    }

    /**
     * Removes a task from list via id and outputs the status.
     * @param id refers to the task number on the list.
     */
    public void removeTaskFromList(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }

            Task taskToRemove = this.taskList.get(id - 1);
            this.taskList.remove(id - 1);
            String taskRemovedOutput = String.format("Noted. I've removed this task:\n %s\n%s\n",
                    taskToRemove.toString(), getItemsLeft());
            System.out.println(taskRemovedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a task to the list
     * @param task task to be added to list
     * @see Task
     */
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

    /**
     * Lists all tasks as numbered list.
     */
    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println(NO_TASKS_LEFT);
            return;
        }
        String toPrint = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toConcat = handleTaskOutput(task, i + 1);
            toPrint = String.format("%s\n%s", toPrint, toConcat);
        }

        System.out.println(toPrint.substring(1) + "\n");
    }

    /**
     * Marks a task as done with regards to given task number.
     * @param id refers to the task number on the list.
     * @see Task
     */
    public void markTaskAsDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            targetTask.markAsDone(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Marks a task as undone with regards to given task number.
     * @param id refers to the task number on the list.
     * @see Task
     */
    public void markTaskAsUnDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            targetTask.markAsUnDone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns last added task output. Recommended for testing purposes.
     * @return output regarding last action.
     */
    public String getAddedTaskOutput() {
        Task lastAddedTask = new Task("", TaskType.TASK);
        if (!taskList.isEmpty()) {
            lastAddedTask = taskList.get(taskList.size() - 1);
            return String.format("Got it. I've added this task:\n  %s\n%s\n",
                    lastAddedTask.toString(), getItemsLeft());
        }
        return NO_TASKS_LEFT;
    }
}
