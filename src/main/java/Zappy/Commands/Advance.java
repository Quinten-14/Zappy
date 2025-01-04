package Zappy.Commands;

public class Advance implements Command {
    @Override
    public void execute() {
        System.out.println("Advancing...");
        // sends message to the server to advance
    }
}
