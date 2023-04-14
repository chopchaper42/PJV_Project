package network.udp.server;

import network.udp.IPManager;
import network.udp.Socket;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class OurServerSocket extends Socket
{
    public OurServerSocket(IPManager ipManager) throws UnknownHostException, SocketException
    {
        super(ipManager);
    }

    public boolean addIPAddress(InetAddress ip)
    {
        if (socketTargets[0] == null)
        {
            socketTargets[0] = ip;
            return true;
        }
        else if (socketTargets[1] == null)
        {
            socketTargets[1] = ip;
            return true;
        }
        else
        {
            return false;
        }
    }
}
