//package network.udp.server;
//
//import network.udp.IPManager;
//import network.udp.Socket;
//
//import java.net.InetAddress;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//
//public class OurServerSocket extends Socket
//{
//    /**
//     * @param ipManager the IPManager object that is used to check the IP addresses of the clients.
//     */
//    public OurServerSocket(IPManager ipManager) throws UnknownHostException, SocketException
//    {
//        super(ipManager);
//    }
//
//    /**
//     * @param ip the IP address of the client.
//     * @return true if the IP address was added to the socketTargets array, false otherwise.
//     */
//    public boolean addIPAddress(InetAddress ip)
//    {
//        if (socketTarget[0] == null)
//        {
//            socketTarget[0] = ip;
//            return true;
//        }
//        else if (socketTarget[1] == null)
//        {
//            socketTarget[1] = ip;
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//}
