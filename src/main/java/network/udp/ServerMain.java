package network.udp;

import java.net.UnknownHostException;

public class ServerMain
{
    public static void main(String[] args) throws UnknownHostException
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