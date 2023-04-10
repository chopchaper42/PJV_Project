package network.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ServerUDP
{
    private  InetAddress IP_ADDRESS;

    {
        try
        {
            IP_ADDRESS = InetAddress.getLocalHost();
        }
        catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }
    }

    private int port;
    private int players;

    private DatagramSocket socket;
    private boolean running;
    private final byte[] receiveBuffer = new byte[256];
    private final byte[] sendBuffer = new byte[256];



    public void start() throws IOException
    {
        if (!ConfigureServer())
            return;

        socket = new DatagramSocket(port, IP_ADDRESS);

        System.out.printf("Listening on udp:%s:%d%n",
                IP_ADDRESS.getHostAddress(), port);
        System.out.println("----------------------------------\n");
        System.out.println(socket.getLocalAddress());

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);

        System.out.println("Port: " + socket.getLocalPort());

        System.out.println("Waiting for a client...");

        while (true) {
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            System.out.println("RECEIVED "  + ": " + receivedMessage);
        }
    }


    private boolean ConfigureServer()
    {
        System.out.println("Welcome to the CaveShooter server!");
        System.out.println("----------------------------------");
        System.out.println("Please, enter the port number:\n");

        Scanner sc = new Scanner(System.in);
        this.port = sc.nextInt();
        System.out.println("----------------------------------");

        System.out.println("Please, enter the number of poor fellows!:\n");
        this.players = sc.nextInt();

        if (this.players < 1 || this.players > 2)
        {
            System.out.println("The number of future dead men can't be less than 1 or more than 2!");
            return false;
        }

        return true;
    }
}
