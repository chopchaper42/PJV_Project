package network.udp;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class Socket
{
    /**
     * The socket that is used to send and receive data.
     */
    private DatagramSocket socket;

    /**
     * The default port that is used to send and receive data.
     */
    private final int SEND_TO_PORT;
    private final int LISTEN_ON_PORT;

    /**
     * The target that the socket will send data to.
     */
    protected InetAddress socketTarget = null;

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
     * The static class IPManager that is used to get the IP address of the local machine.
     */
    public Socket(boolean isHostingServer) throws UnknownHostException, SocketException
    {
        if (isHostingServer)
        {
            SEND_TO_PORT = 12345;
            LISTEN_ON_PORT = 12346;
            socket = new DatagramSocket(LISTEN_ON_PORT);
        }
        else
        {
            SEND_TO_PORT = 12346;
            LISTEN_ON_PORT = 12345;
            socket = new DatagramSocket(LISTEN_ON_PORT);
        }
    }

    /**
     * Sends a message to the targets.
     * @param message The message that is sent.
     */
    public void send(String message)
    {
        sendBuffer = message.getBytes(StandardCharsets.UTF_8);
        try
        {
            DatagramPacket sendingPacket = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    socketTarget,
                    SEND_TO_PORT
            );

            socket.send(sendingPacket);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setTarget(InetAddress target)
    {
        socketTarget = target;
    }



    /**
     * Listens for incoming messages.
     * @return The message that was received.
     */
    public DatagramPacket listen()
    {
        receiveBuffer = new byte[256];
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

    public void close()
    {
        socket.close();
    }
}
