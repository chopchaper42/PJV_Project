package network.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPManager
{
    /**
     * The IP address of the local machine.
     */
    public static InetAddress getMyIP() throws UnknownHostException
    {
        return InetAddress.getLocalHost();
    }

    /**
     * Checks if the IP address of the client is valid.
     * @param deadFellowIP the IP address of the client.
     */
    public static boolean checkIP(String deadFellowIP)
    {
        String regex = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
        if (deadFellowIP.matches(regex))
        {
            System.out.println("IP is valid");
            System.out.println(deadFellowIP);
            return true;
        }
        else
        {
            System.out.println("IP is not valid");
            System.out.println(deadFellowIP);

            return false;
        }
    }
}
