//package network.udp.server;
//
//import java.io.IOException;
//import java.net.*;
//import java.util.Scanner;
//
//import network.udp.IPManager;
//
//import network.udp.server.ServerLogger;
//
//
//public class ServerPlayersConnection
//{
//    /**
//     * The number of players in the game.
//     */
//    private int numOfConnectingPlayers;
//
//    /**
//     * The packet that is received from the client.
//     */
//    private DatagramPacket receivePacket;
//
//    private final String filePath = "./src/main/levels";
//
//    public ServerPlayersConnection() throws UnknownHostException
//    {
//    }
//
//    /**
//     * start() method is the main method of the server.
//     */
//    public void start() throws IOException
//    {
//        selectThePlayersNumber();
//
//        var ipManager = new IPManager();
//
//        var serverSocket = new OurServerSocket(ipManager);
//
//        while (numOfConnectingPlayers > 0) {
//
//            receivePacket = serverSocket.listen();
//            var ipOfNewPlayer = receivePacket.getAddress();
//
//            if (ipManager.checkIP(ipOfNewPlayer.toString()))
//            {
//                ServerLogger.log("Another dead fellow has been found.. HA-HA-HA!");
//                ServerLogger.log("--------------------\n");
//                numOfConnectingPlayers--;
//                serverSocket.addIPAddress(ipOfNewPlayer);
//            }
//        }
//
//        ServerLogger.log("Server is running...");
//        ServerLogger.log("--------------------\n");
//
//
//        InetAddress[] targets = serverSocket.getTargets();
//
//        // send message to start the game to the clients
//        for (InetAddress target : targets)
//        {
//            serverSocket.send("start", target);
//            ServerLogger.log("start message sent to " + target);
//        }
//
//        serverSocket.close();
//        ServerLogger.log("Server is closed...");
//        ServerLogger.log("--------------------\n");
//
//
//        System.out.println("The game is starting...");
//        ServerLogger.log("The game is starting...");
//
//        var serverController =  new ServerController(serverSocket);
//        serverController.start();
//    }
//
//    public void selectThePlayersNumber()
//    {
//        ServerLogger.log("Selecting the number of players...");
//
//        Scanner in = new Scanner(System.in);
//
//        int players = 0;
//
//        while (players < 1 || players > 2)
//        {
//            System.out.println("How many dead fellows today?");
//            players = Integer.parseInt(in.nextLine());
//            System.out.println("--------------------\n");
//
//            if (players < 1 || players > 2)
//            {
//                System.out.println("Either one dead fellow or two dead fellows is enough for today...");
//                System.out.println("--------------------\n");
//                ServerLogger.log("The number of players is not valid. Try again.");
//            }
//        }
//        this.numOfConnectingPlayers = players;
//        ServerLogger.log("The number of players is " + players);
//    }
//}
