package Zappy.Client.Initialise;

import static java.lang.System.exit;

public class ParseInput {
    private static String team = null;
    private static String host = "localhost";
    private static final String portStr = null;
    private static int port = 0;

    public ParseInput(String[] args)
    {
        handleData(args);
    }

    private static void handleData(String[] args) {
        String portStr = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n" -> team = args[++i];
                case "-p" -> portStr = args[++i];
                case "-h" -> host = args[++i];
                default -> exit(1);
            }
        }
        if (portStr == null || team == null)
            exit(1);
        try {
            port = Integer.parseInt(portStr);
            portStr = null;
        } catch (NumberFormatException e) {
            exit(1);
        }
    }

    public String getTeam() {
        return team;
    }

    public String getHost() {
        return host;
    }

    public int getPort()
    {
        return port;
    }
}
