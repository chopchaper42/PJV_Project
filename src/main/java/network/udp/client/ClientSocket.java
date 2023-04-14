package network.udp.client;

import network.udp.IPManager;
import network.udp.Socket;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class ClientSocket extends Socket
{
    /**
     * Constructor for ClientSocket
     * @param serverIP IP address of the server
     * @param ipManager IPManager object which is necessary for the socket
     */
    public ClientSocket(String serverIP, IPManager ipManager) throws UnknownHostException, SocketException
    {
        super(ipManager);

        // add server IP to socketTargets to send messages to it
        if (socketTargets[0] == null)
        {
            socketTargets[0] = InetAddress.getByName(serverIP);
        }
    }
}
