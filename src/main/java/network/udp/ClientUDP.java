package network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ClientUDP
{
    public final int DEFAULT_PORT = 10421;

    public final String SERVER_IP = "";

    public void send() throws IOException
    {
        Scanner scr = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);
        byte[] sendingBuffer = new byte[256];

        System.out.println("Type your message:");

        String message = scr.next();
        sendingBuffer = message.getBytes(StandardCharsets.UTF_8);

        DatagramPacket sendingPacket = new DatagramPacket(
                sendingBuffer,
                sendingBuffer.length,
                InetAddress.getByName(SERVER_IP),
                DEFAULT_PORT
        );

        socket.send(sendingPacket);
    }
}