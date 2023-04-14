package network.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPManager
{
    /**
     * The IP address of the local machine.
     */
    private final InetAddress localMachineIP = InetAddress.getLocalHost();

    public IPManager() throws UnknownHostException
    {

    }

    public InetAddress getMyIP()
    {
        return localMachineIP;
    }

    /**
     * Checks if the IP address of the client is valid.
     * @param deadFellowIP the IP address of the client.
     */
    public boolean checkIP(String deadFellowIP)
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

    /**
     * Extracts the IP address from the DatagramPacket.
     * @param receivePacket the DatagramPacket that contains the IP address.
     * @return the IP address of the client.
     */
    public String extractIP(DatagramPacket receivePacket)
    {
        String ip = receivePacket.getAddress().toString();
        ip = ip.substring(1);
        return ip;
    }
}
