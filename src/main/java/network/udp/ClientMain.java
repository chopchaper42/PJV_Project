package network.udp;

import java.io.IOException;

public class ClientMain
{
    public static void main(String[] args) throws IOException
    {
        var clientUDP = new ClientUDP();
        clientUDP.send();
        System.out.println("Done");
    }

}
