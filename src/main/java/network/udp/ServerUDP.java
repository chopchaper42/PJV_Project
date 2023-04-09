package network.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class ServerUDP
{
    private DatagramSocket socket;
    private boolean running;
    private final byte[] receiveBuffer = new byte[256];
    private final byte[] sendBuffer = new byte[256];
    //private final InetAddress IP_ADDRESS = InetAddress.getByName("172.21.7.2");
    private final InetAddress IP_ADDRESS = InetAddress.getByName("192.168.43.249");
    private final int PORT = 10422;

    public ServerUDP() throws UnknownHostException
    {
    }

    public void start() throws IOException
    {
        InetAddress serverAddress = IP_ADDRESS;
        socket = new DatagramSocket(PORT, IP_ADDRESS);

        System.out.printf("Listening on udp:%s:%d%n",
                IP_ADDRESS.getHostAddress(), PORT);
        System.out.println(socket.getLocalAddress());

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);
        int counter = 1;
        while (true) {
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            System.out.println("RECEIVED " + counter++ + ": " + receivedMessage);
        }
    }
}
