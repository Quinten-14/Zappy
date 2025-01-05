package Zappy.Client.Initialise;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

import Zappy.Client.Game.Loop;

public class Client
{
    //Storage variables for the team name, the port integer and the host ip
    private final String team;
    private String host = "localhost";
    private final int port;
    private final ExecutorService executorService;
    private volatile boolean spawnReceived = false;
    private volatile String spawnLocation = null;
    private static final Logger logger = Logger.getLogger(Client.class.getName());

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
                        } else if (parts.length == 4)
                        {
                            spawnLocation = response;
                            spawnReceived = true;
                            System.out.println("Spawn location: " + spawnLocation);
                        }
                    }
                    Loop loop = new Loop();
                    loop.startGameLoop(socket);
                }
                catch (IOException e)
                {
                    logger.severe("IO Exception: " + e.getMessage());
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
    }
}
