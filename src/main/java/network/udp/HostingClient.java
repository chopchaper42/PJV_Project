package network.udp;

import Engine.GameSettings;

import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HostingClient {

    Socket socket;

    public HostingClient() throws SocketException, UnknownHostException
    {
        socket = new Socket(true);
    }

    public void waitForConnection()
    {
        System.out.println("Waiting for connection...");
        // wait for connection
        DatagramPacket receivePacket = socket.listen();

        // set target
        socket.setTarget(receivePacket.getAddress());
        System.out.println("Connected to " + receivePacket.getAddress());

        // send confirmation
        socket.send(".src/main/levels/level1ForTwoPlayers.txt");
        System.out.println("Level sent");
    }

    public Socket getSocket() {
    	return socket;
    }
}
