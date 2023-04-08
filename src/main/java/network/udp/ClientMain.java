package network.udp;

import java.io.IOException;

public class ClientMain
{
    public static void main(String[] args) throws IOException
    {
        //MyClient.main();
        ClientUDP.send();
        System.out.println("Done");
    }

}
