package network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ClientUDP
{
    public static final int DEFAULT_PORT = 10000;
//    public static final int TARGET_PORT = 10422;
    public static final String LOCAL_SERVER_IP = "172.20.10.4";
    public static final String SERVER_IP = "172.20.10.7";

    public static void send() throws IOException
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
//        for (int i = 0; i < 100; i++) {
//            socket.send(sendingPacket);
//        }


        /*Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket(6001);
        byte[] buff;
        String msg = "";
        while (!msg.equals("end")) {
            msg = sc.next();
            buff = msg.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(
                    buff,
                    buff.length,
                    InetAddress.getByName(EGOR_SERVER_IP_2),
                    DEFAULT_PORT
            );
            System.out.println("Sending to: " + packet.getAddress() + ":" + packet.getPort());
            for (int i = 0; i < 100; i++)
                socket.send(packet);
            try {
                socket.setSoTimeout(5000);
                socket.receive(packet);
                String answer = new String(packet.getData(), 0, packet.getLength());
                System.out.println(answer);
            } catch (SocketTimeoutException e) {
                System.out.println(e.getMessage());
            }
        }*/
    }


}
