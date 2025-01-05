package Client.Initialise;

import java.io.*;
import java.net.*;
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

    public Client(String teamName, String hostName, int port)
    {
        this.team = teamName;
        this.port = port;
        if (!hostName.isEmpty())
            this.host = hostName;
        executorService = Executors.newCachedThreadPool();
    }


    public void connect()
    {
        //try to connect to server using host:port and send the string 'team' as the first communication.
        // If the server responds with 1 integer, it wants us to make that amount of threads and connect them one by one.
        // If it responds with 2 integers, continue to game loop
        // Each 'player client' will be represented by a thread, also the original connection, meaning we make 1 more than requested
        try
        {
            while (!spawnReceived)
            {
                executorService.submit(() ->
                {
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
            loop.startGameLoop();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
