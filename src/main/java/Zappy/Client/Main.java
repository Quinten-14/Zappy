package Zappy.Client;


import Zappy.Client.Initialise.Client;
import Zappy.Client.Initialise.ParseInput;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
        {
            System.out.println("Usage: java Client -n <team-name> -p <port> [-h <hostname>]");
            System.out.println("-n team name");
            System.out.println("-p port");
            System.out.println("-h name of the host, by default it'll be localhost");
            return;
        }
        ParseInput input = new ParseInput(args);
        Client client = new Client(input.getTeam(), input.getHost(), input.getPort());
        client.connect();
    }
}


