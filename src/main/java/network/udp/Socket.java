package network.udp;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public abstract class Socket
{
    private DatagramSocket socket;

    private final int DEFAULT_PORT = 10421;

    protected InetAddress[] socketTargets = new InetAddress[2];

    private byte[] receiveBuffer = new byte[256];
    private byte[] sendBuffer = new byte[256];

    public Socket(IPManager ipManager) throws UnknownHostException, SocketException
    {
        socket = new DatagramSocket(DEFAULT_PORT, ipManager.getMyIP());
    }

    public void send(String message)
    {
        sendBuffer = message.getBytes(StandardCharsets.UTF_8);

        for (InetAddress target : socketTargets)
        {
            if (target != null)
            {
                try
                {
                    DatagramPacket sendingPacket = new DatagramPacket(
                            sendBuffer,
                            sendBuffer.length,
                            target,
                            DEFAULT_PORT
                    );

                    socket.send(sendingPacket);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public DatagramPacket listen()
    {
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);

        try
        {
            socket.receive(receivePacket);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return receivePacket;
    }

    public int getPort()
    {
        return DEFAULT_PORT;
    }
}
