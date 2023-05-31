//package network.udp.server;
//
//import network.udp.client.JSONManager;
//
//import java.net.DatagramPacket;
//import java.net.ServerSocket;
//import java.util.Arrays;
//
//
//public class ServerController
//{
//    /**
//     * Manages JSONs
//     */
//    private JSONManager jsonManager;
//
//    /**
//     * Synchronizes the game state among all clients
//     */
//    private GameStateSynchronizer gameStateSynchronizer;
//
//
//    /**
//     * Server socket
//     */
//    private OurServerSocket serverSocket;
//
//    /**
//     * Packet to receive messages
//     */
//    DatagramPacket receivePacket;
//
//    ServerController(OurServerSocket serverSocket)
//    {
//        this.serverSocket = serverSocket;
//        jsonManager = new JSONManager();
//        gameStateSynchronizer = new GameStateSynchronizer(serverSocket);
//    }
//
//    public void start()
//    {
//        while (true)
//        {
//            receivePacket = serverSocket.listen();
//            int clientMessageFromID = serverSocket.getID(receivePacket.getAddress());
//
//            if (clientMessageFromID == -1)
//            {
//
//                ServerLogger.log("Error: client not found");
//                continue;
//            }
//            ServerLogger.log("Player " + clientMessageFromID + " has sent a message");
//
//            ServerLogger.log("--------------------\n");
//
//            // If the message is "game over", then the other client is sent the same message and the server shuts down
//            if (receivePacket.getData().toString().equals("game over"))
//            {
//                ServerLogger.log("Player " + clientMessageFromID + " has lost the game");
//                gameStateSynchronizer.endTheGame();
//                ServerLogger.log("Game is over...");
//                ServerLogger.log("--------------------\n");
//                ServerLogger.log("Server is shutting down...");
//                ServerLogger.log("--------------------\n");
//                break;
//            }
//
//            // If the message is not "game over", then the game state is synchronized among all clients
//            gameStateSynchronizer
//                    .synchronizeGameBetweenClients(Arrays.toString(receivePacket.getData()),
//                            clientMessageFromID);
//            ServerLogger.log("Server sent the game state to another client");
//        }
//    }
//}
