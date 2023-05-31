package network.udp.client;

import java.net.*;
import java.util.Scanner;

import Logs.*;

import network.udp.IPManager;

public class ClientConnection
{
    /**
     * Runs the client
     */
    public void run() throws SocketException, UnknownHostException
    {
        var ipManager = new IPManager();

        Scanner scr = new Scanner(System.in);
        Logger.log("Type the server's IP address:\n");
        System.out.println("Type the server's IP address:\n");
        String serverIP = scr.next();

        if (!ipManager.checkIP(serverIP))
        {
            Logger.log("Invalid IP address");
            System.out.println("Invalid IP address");
            Logger.log("--------------------\n");
            System.out.println("--------------------\n");
            return;
        }

        Logger.log("--------------------\n");
        System.out.println("--------------------\n");
        Logger.log("Client is running...");
        System.out.println("Client is running...");
        System.out.println("--------------------\n");
        Logger.log("--------------------\n");

//        var clientSocket = new ClientSocket(serverIP, ipManager);

//        clientSocket.send("Hello, server!", clientSocket.getTargets()[0]);

        Logger.log("Message sent");
        Logger.log("--------------------\n");
        System.out.println("--------------------\n");
        System.out.println("Message sent");
        System.out.println("--------------------\n");
        Logger.log("--------------------\n");

//        ClientControllerSingleton.setController(new ClientController(clientSocket));
    }
}