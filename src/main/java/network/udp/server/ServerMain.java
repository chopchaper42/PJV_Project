package network.udp.server;

public class ServerMain
{
    /**
     * The main method of the server.
     */
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