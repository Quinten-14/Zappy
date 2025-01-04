package Client.Initialise;

import static java.lang.System.exit;

public class InitClient
{
    //Storage variables for the team name, the port integer and the host ip
    String team = null, portStr = null, host = "localhost";
    int port = 0;

    //Converts input data to local data
    private void handleData(String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            switch (args[i]) {
                case "-n" -> team = args[++i];
                case "-p" -> portStr = args[++i];
                case "-h" -> host = args[++i];
                default -> exit(1);
            }
        }
        if (portStr == null || team == null)
            exit(1);
        try
        {
            port = Integer.parseInt(portStr);
            portStr = null;
        }
        catch (NumberFormatException e)
        {
            exit(1);
        }
    }

    private void connect()
    {
        //try to connect to server using host:port and send the string 'team' as the first communication.
        // If the server responds with 1 integer, it wants us to make that amount of threads and connect them one by one.
        // If it responds with 2 integers, continue to game loop
    }

    public void initClient(String[] args)
    {
        handleData(args);
        connect();
    }
}
