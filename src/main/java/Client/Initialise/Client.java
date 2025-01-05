package Client.Initialise;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Client.Game.Loop;

public class Client
{
    //Storage variables for the team name, the port integer and the host ip
    private String team = null;
    private final String portStr = null;
    private String host = "localhost";
    private int port = 0;
    private final ExecutorService executorService;
    private volatile boolean spawnReceived = false;
    private volatile String spawnLocation = null;
    private ConcurrentHashMap<Long, Socket> threadSocketMap = new ConcurrentHashMap<>();

    public Client(String teamName, String hostName, int port)
    {
        this.team = teamName;
        this.port = port;
        if (!hostName.isEmpty())
            this.host = hostName;
        executorService = Executors.newCachedThreadPool(); //The cached pool thread dynamically expands as more threads are made until shut down.
    }


    public void connect()
    {
        try
        {
            while (!spawnReceived)
            {
                executorService.submit(() ->
                {
                    //Socket is used to create a connection between the server and the client
                    //BufferedReader is used to receive responses from the server
                    //PrintWriter is used to send requests to the server
                    try (Socket socket = new Socket(host, port);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
                    {
                        out.println(team);
                        String response = in.readLine();

                        if (response != null)
                        {
                            String[] parts = response.split(" ");
                            if (parts.length == 1)
                            {
                                System.out.println("Waiting for more players..");
                            }
                            else if (parts.length == 2)
                            {
                                spawnLocation = response;
                                spawnReceived = true;
                                System.out.println("Spawn location: " + spawnLocation);
                            }
                            long threadId = Thread.currentThread().threadId();
                            threadSocketMap.put(threadId, socket);
                        }

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
                Thread.sleep(100);
            }
            executorService.shutdown();
            Loop loop = new Loop();
            loop.startGameLoop(threadSocketMap);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
