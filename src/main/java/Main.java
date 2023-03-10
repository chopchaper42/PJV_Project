import network.udp.ServerUDP;

import java.io.IOException;
import java.net.SocketException;

public class Main
{
    public static void main(String[] args)
    {
        ServerUDP server = null;
        try
        {
            server = new ServerUDP();
            server.start();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}