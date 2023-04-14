package network.udp.client;

import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import network.udp.IPManager;

public class ClientUDP
{
    /**
     * Runs the client
     */
    public void run() throws SocketException, UnknownHostException
    {
        var ipManager = new IPManager();

        Scanner scr = new Scanner(System.in);
        System.out.println("Type the server's IP address:\n");
        String serverIP = scr.next();

        if (!ipManager.checkIP(serverIP))
        {
            System.out.println("Invalid IP address");
            System.out.println("--------------------\n");
            return;
        }

        System.out.println("--------------------\n");
        System.out.println("Client is running...");
        System.out.println("--------------------\n");

        var clientSocket = new ClientSocket(serverIP, ipManager);

        clientSocket.send("Hello, server!");

        System.out.println("Message sent");
        System.out.println("--------------------\n");


        // @TO-DO: need to refactor a bit to make ClientUDP the start point
        // CaveShooter.main() right now is the start point;


        while (true)
        {
            String message = Arrays.toString(clientSocket.listen().getData());

            if (message.equals("exit"))
            {
                System.out.println("Client is closing...");
                break;
            }

            System.out.println("Message received: " + message);
            System.out.println("--------------------\n");

            // Change the state according to the message
            // @TO-DO: implement sending messages to the server
        }
    }
}