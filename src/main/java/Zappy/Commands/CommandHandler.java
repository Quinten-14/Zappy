package Zappy.Commands;

import java.util.Map;

public class CommandHandler {
    Map<String, Command> commands;

    public CommandHandler() {
        this.commands = Map.of(
            "advance", new Advance()
        );
    }

    public void executeCommand(String command) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute();
        } else {
            System.out.println("Command not found.");
        }
    }
}
