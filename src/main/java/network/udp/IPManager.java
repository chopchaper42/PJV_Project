package network.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPManager
{
    private final InetAddress localMachineIP = InetAddress.getLocalHost();

    public IPManager() throws UnknownHostException
    {

    }

    public InetAddress getMyIP()
    {
        return localMachineIP;
    }

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

    public String extractIP(DatagramPacket receivePacket)
    {
        String ip = receivePacket.getAddress().toString();
        ip = ip.substring(1);
        return ip;
    }
}
