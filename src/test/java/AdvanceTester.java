import Zappy.Commands.CommandHandler;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvanceTester {
    @Test
    public void testAdvance() {
        // Arrange
        CommandHandler handler = new CommandHandler();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = new String[] {"advance"};
        handler.executeCommand(args);

        assertEquals("Advancing...\n", outContent.toString());
    }

    @Test
    public void testExecuteCommandNotFound() {
        // Arrange
        CommandHandler handler = new CommandHandler();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = new String[] {"advance", "arg"};
        handler.executeCommand(args);

        assertEquals("Invalid number of arguments.\n", outContent.toString());
    }
}