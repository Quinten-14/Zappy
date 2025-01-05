package Zappy.Client.Game;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Loop
{
    public void startGameLoop(ConcurrentHashMap<Long, Socket> threadSocketMap)
    {
        Socket socket = threadSocketMap.get(Thread.currentThread().threadId());
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
