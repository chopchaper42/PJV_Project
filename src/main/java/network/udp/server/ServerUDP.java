package network.udp.server;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import network.udp.IPManager;

public class ServerUDP
{
    private int players;

    DatagramPacket receivePacket;
//    DatagramPacket sendingPacket;

    public ServerUDP() throws UnknownHostException
    {
    }

    public void start() throws IOException
    {
        selectThePlayersNumber();

        var ipManager = new IPManager();

        var serverSocket = new OurServerSocket(ipManager);

        while (players > 0) {

            receivePacket = serverSocket.listen();
            var ipOfNewPlayer = receivePacket.getAddress();

            if (ipManager.checkIP(ipOfNewPlayer.toString()))
            {
                System.out.println("Another dead fellow has been found.. HA-HA-HA!");
                System.out.println("--------------------\n");
                players--;
                serverSocket.addIPAddress(ipOfNewPlayer);
            }
        }

        System.out.println("Server is running...");
        System.out.println("--------------------\n");

        System.out.println("The game is starting...");

        // @TO-DO: redirect messages from the loop to the logger
        while (true)
        {
            receivePacket = serverSocket.listen();
            var ipOfSender = receivePacket.getAddress().toString();

            String message = Arrays.toString(receivePacket.getData());

            System.out.println("Message from client: " + message + "\nIP:" + ipOfSender);
            System.out.println("--------------------\n");

            // @TO-DO: validate the message and send the response
        }
    }

    public void selectThePlayersNumber()
    {
        Scanner in = new Scanner(System.in);

        int players = 0;

        while (players < 1 || players > 2)
        {
            System.out.println("How many dead fellows today?");
            players = Integer.parseInt(in.nextLine());;
            System.out.println("--------------------\n");

            if (players < 1 || players > 2)
            {
                System.out.println("Either one dead fellow or two dead fellows is enough for today...");
                System.out.println("--------------------\n");
            }
        }
        this.players = players;
    }
}