package Zappy.Client.Game;

import Zappy.Client.Player.Player;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import static java.lang.Thread.sleep;

public class Loop
{
    private final int millisecondMultiplier = 1000; //Change this so clients don't wait the full wait time between commands (Instead of seconds, you can make it hundreds of milliseconds etc)
    public Loop()
    {

    }

    public void startGameLoop(ConcurrentHashMap<Long, Socket> threadSocketMap) throws InterruptedException
    {
        boolean gameRunning = true;
        Algorithm algorithm = new Algorithm();
        CommandHandler command = new CommandHandler();
        Player player = new Player();

        Socket socket = threadSocketMap.get(Thread.currentThread().threadId());
        while(gameRunning) {
            //The algorithm will return a string clarifying which command to run.
            String request = algorithm.run(player);
            //In the CommandHandler, the values of the player will get modified and the correct string will be sent to the server
            String response = command.sendToServer(player, request, socket);

            if (response != null && response.equals("Game Over"))
            {
                gameRunning = false;
            }
            else if (response != null)
            {
                sleep((long) player.getDelay() * millisecondMultiplier);
                player.setHealth(player.getDelay() * -1);
                if (response.contains("Food"))
                {
                    player.setHealth(126);
                }
            }
        }

    }
}
