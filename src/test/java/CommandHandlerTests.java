import Zappy.Commands.CommandHandler;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandHandlerTests {
    @Test
    public void testExecuteCommand() {
        // Arrange
        CommandHandler handler = new CommandHandler();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.executeCommand("advance");

        assertEquals("advancing\n", outContent.toString());
    }

    @Test
    public void testExecuteCommandNotFound() {
        // Arrange
        CommandHandler handler = new CommandHandler();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        handler.executeCommand("invalid");

        assertEquals("Command not found.\n", outContent.toString());
    }
}