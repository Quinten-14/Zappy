package Zappy.Commands;

import java.util.Map;

public class CommandHandler {
    Map<String, Command> commands;

    public CommandHandler() {
        this.commands = Map.of(
            "advance", new Advance()
        );
    }

    public void executeCommand(String[] args) {
        Command cmd = commands.get(args[0]);
        if (cmd != null) {
            cmd.execute(args);
        } else {
            System.out.println("Command not found.");
        }
    }
}
