package Zappy.Commands;

public class Advance implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            return;
        }

        System.out.println("Advancing...");
        // sends message to the server to advance
    }
}
