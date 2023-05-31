package network.udp.client;

import java.io.IOException;

public class ClientMain
{
    public static void main(String[] args) throws IOException
    {
        var clientUDP = new ClientConnection();
        clientUDP.run();
    }
}
