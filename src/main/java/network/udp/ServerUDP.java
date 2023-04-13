package network.udp;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class ServerUDP
{
    private DatagramSocket socket;
    private String[] ipList = new String[2];
    private final byte[] receiveBuffer = new byte[256];
    private final byte[] sendBuffer = new byte[256];
    private final InetAddress IP_ADDRESS = InetAddress.getLocalHost();
    private int port;
    private int players;

    public ServerUDP() throws UnknownHostException
    {
        ipList[0] = null;
        ipList[1] = null;
    }

    public void start() throws IOException
    {
        configureTheServer();

        InetAddress serverAddress = IP_ADDRESS;
        socket = new DatagramSocket(port, IP_ADDRESS);

        System.out.printf("Listening on udp:%s:%d%n",
                IP_ADDRESS.getHostAddress(), port);
        System.out.println(socket.getLocalAddress());

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);


        while (players > 0) {

            socket.receive(receivePacket);


            String ip = extractIP(receivePacket);

            if (checkIP(ip))
            {
                System.out.println("Another dead fellow has been found.. HA-HA-HA!");
                System.out.println("His ip is: " + ip);
                players--;
                ipList[players] = ip;Scanner in = new Scanner(System.in);
            }
        }

        System.out.println("The game is starting...");



    }

    private String extractIP(DatagramPacket receivePacket)
    {
        String ip = receivePacket.getAddress().toString();
        ip = ip.substring(1);
        return ip;
    }

    public void configureTheServer()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("What port?");
        port = Integer.parseInt(in.nextLine());
        System.out.println("--------------------");

        int players = 0;

        while (players < 1 || players > 2)
        {
            System.out.println("How many dead fellows today?");
            players = Integer.parseInt(in.nextLine());;
            System.out.println("--------------------");

            if (players < 1 || players > 2)
            {
                System.out.println("Either one dead fellow or two dead fellows is enough for today...");
            }
        }
        this.players = players;
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
}