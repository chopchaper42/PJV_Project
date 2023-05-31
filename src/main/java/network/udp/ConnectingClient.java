package network.udp;

import Engine.Game;
import Engine.GameSettings;
import Engine.Level.LevelManager;
import GUI.GUIManager;

import java.io.File;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class ConnectingClient
{
    Socket socket;
    public ConnectingClient() throws SocketException, UnknownHostException
    {
        socket = new Socket(false);
    }

    public void connect(String serverIP) throws UnknownHostException
    {
        socket.setTarget(InetAddress.getByName(serverIP));
        socket.send(IPManager.getMyIP().toString());
        System.out.println("Connected to " + serverIP);

    }

    public File receiveLevel()
    {
        // wait for confirmation
        DatagramPacket receivePacket = socket.listen();
        // return the level file
        return new File(".src/main/levels/" + new String(receivePacket.getData()));
    }

    public Socket getSocket()
    {
        return socket;
    }


}
