package network;

import java.io.IOException;

public class ServerMain
{
    private static final int DEFAULT_PORT = 6000;
    public static void main(String[] args)
    {
        ServerTCP server = new ServerTCP();
        boolean connection = server.openConnectionOnPort(DEFAULT_PORT);
        try {
            server.listen();
        } catch (IOException e) {
            System.out.println("Listening failed");
        }
    }
}
