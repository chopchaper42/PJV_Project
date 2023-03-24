package network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class UDP
{
    public static final int DEFAULT_PORT = 10421;
    public static final String LOCAL_SERVER_IP = "127.0.0.1";
    public static final String SERVER_IP = "172.21.7.2";
    public static final String DANYA_SERVER_IP = "172.21.14.1";

    public static void send() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket(6001);
        byte[] buff;
        String msg = "";
        while (!msg.equals("end")) {
            msg = sc.next();
            buff = msg.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    buff,
                    buff.length,
                    InetAddress.getByName(SERVER_IP),
                    DEFAULT_PORT
            );
            System.out.println("Sending to: " + packet.getAddress() + ":" + packet.getPort());
            socket.send(packet);
            try {
                socket.setSoTimeout(5000);
                socket.receive(packet);
                String answer = new String(packet.getData(), 0, packet.getLength());
                System.out.println(answer);
            } catch (SocketTimeoutException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
