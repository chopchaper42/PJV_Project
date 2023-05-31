package network.udp;

import Engine.CaveShooter;
import Engine.Game;
import Engine.GameSettings;

import Engine.InventoryManager;
import network.udp.client.ClientController;
import network.udp.client.ClientControllerSingleton;

import java.io.File;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

public class ClientPlayersConnection
{

    public ClientPlayersConnection()
    {
    }

    public static void main(String[] args) throws SocketException, UnknownHostException
    {
        connectTwoPlayers();
    }

    public static void connectTwoPlayers() throws SocketException, UnknownHostException
    {
        System.out.println("Which type of game");
        System.out.println("1. Create a Game");
        System.out.println("2. Connect to the existing Game");
        System.out.println("Enter the number of the option: ");

        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();

        if (option == 1)
        {
            var hostingClient = new HostingClient();
            hostingClient.waitForConnection();

            // create the controller
            ClientController clientController = new ClientController(hostingClient.getSocket());
            ClientControllerSingleton.setController(clientController);
            // run the game
            CaveShooter.main(new String[]{}); // run the game
        }
        else
        {

            ConnectingClient connectingClient = new ConnectingClient();

            System.out.println("Enter the IP of the server: ");


//            String serverIP = scanner.nextLine();
            String serverIP = scanner.next();

            connectingClient.connect(serverIP);
            connectingClient.receiveLevel();
            System.out.println("Level received");
            // create the controller
            ClientController clientController = new ClientController(connectingClient.getSocket());
            ClientControllerSingleton.setController(clientController);
            System.out.println("Client Controller set");
            // run the game
            System.out.println("Cave shooter is starting now:");
            CaveShooter.main(new String[]{}); // run the game
        }

    }
}
