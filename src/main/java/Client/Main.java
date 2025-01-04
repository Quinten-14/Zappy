package Client;

import Client.Game.Loop;
import Client.Initialise.InitClient;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
        {
            System.out.println("Usage: java Client -n <teamname> -p <port> [-h <hostname>]");
            System.out.println("-n team name");
            System.out.println("-p port");
            System.out.println("-h name of the host, by default it'll be localhost");
            return;
        }

        InitClient client = new InitClient();
        client.initClient(args);
    }
}
