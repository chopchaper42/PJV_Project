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
    private final InetAddress IP_ADDRESS = InetAddress.getByName("172.21.7.2");
    private final int PORT = 10422;

    public ServerUDP() throws SocketException, UnknownHostException
    {

    }

    public void start() throws IOException
    {
        InetAddress serverAddress = IP_ADDRESS;
//      socket = new DatagramSocket(10422, address);
        socket = new DatagramSocket(PORT, IP_ADDRESS);

        System.out.printf("Listening on udp:%s:%d%n",
                IP_ADDRESS.getHostAddress(), PORT);

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);

        while (true) {
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            System.out.println("RECEIVED: " + receivedMessage);
        }

        /*running = true;
        int counter = 0;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println(socket.getLocalAddress() + ":" +  socket.getLocalPort());
        System.out.println("Up and running");

        while (running)
        {
            socket.receive(packet);
            counter++;
            String address = packet.getAddress().toString();
            int port = packet.getPort();

            System.out.println("Received a packet from: " + address + ":" + port + "\t" + counter);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println(received);

            DatagramPacket answer = new DatagramPacket(
                    buf,
                    buf.length,
                    packet.getAddress(),
                    port
            );
            socket.send(answer);
        }*/
    }
}
