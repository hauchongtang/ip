package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class Command {
    /**
     * Execute the particular command
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     * @throws DukeException
     * @see TaskList
     * @see Ui
     * @see Storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        System.out.println("Parent class Command executed");
        return "";
    }

    /**
     * Returns true if command is instanceof ExitCommand otherwise false
     * @param command
     * @return whether given command is instanceof ExitCommand.
     * @see ExitCommand
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
