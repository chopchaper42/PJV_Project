package network.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class ServerUDP
{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public ServerUDP() throws SocketException, UnknownHostException
    {
        InetAddress address = InetAddress.getByName("172.26.112.1");
        socket = new DatagramSocket(10422, address);
    }

    public void start() throws IOException
    {
        running = true;
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
        }
    }
}
