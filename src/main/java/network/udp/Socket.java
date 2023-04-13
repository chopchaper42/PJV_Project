package network.udp;

import java.net.*;

public abstract class Socket
{
    private DatagramSocket socket;
    private final InetAddress localMachineIP = InetAddress.getLocalHost();

    private final int DEFAULT_PORT = 10421;

    private String[] socketTarget = new String[2];

    private final byte[] receiveBuffer = new byte[256];
    private final byte[] sendBuffer = new byte[256];

    public Socket() throws UnknownHostException, SocketException
    {
        socket = new DatagramSocket(DEFAULT_PORT, localMachineIP);
    }

    public addTarget(String ip)
    {
        socketTarget[0] = ip;
    }

    public void send()
    {

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
        return port;
    }
}
