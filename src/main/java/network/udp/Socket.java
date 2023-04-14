package network.udp;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public abstract class Socket
{
    /**
     * The socket that is used to send and receive data.
     */
    private DatagramSocket socket;

    /**
     * The default port that is used to send and receive data.
     */
    private final int DEFAULT_PORT = 10421;

    /**
     * The targets that the socket will send data to.
     */
    protected InetAddress[] socketTargets = new InetAddress[2];

    /**
     * The buffer that is used to receive data.
     */
    private byte[] receiveBuffer = new byte[256];

    /**
     * The buffer that is used to send data.
     */
    private byte[] sendBuffer = new byte[256];

    /**
     * Creates a new socket.
     * @param ipManager The IPManager that is used to get the IP address of the local machine.
     */
    public Socket(IPManager ipManager) throws UnknownHostException, SocketException
    {
        socket = new DatagramSocket(DEFAULT_PORT, ipManager.getMyIP());
    }

    /**
     * Sends a message to the targets.
     * @param message The message that is sent.
     */
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

    /**
     * Listens for incoming messages.
     * @return The message that was received.
     */
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
}
