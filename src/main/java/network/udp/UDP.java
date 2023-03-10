package network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;



public class UDP
{
    public static final int DEFAULT_PORT = 6000;
    public static final String LOCAL_SERVER_IP = "127.0.0.1";
    public static final String SERVER_IP = "172.21.7.2";

    public static void send() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket(6001);
        byte[] buff;

        String inp = sc.next();
        buff = inp.getBytes();

        DatagramPacket DpSend = new DatagramPacket(
                buff,
                buff.length,
                InetAddress.getByName(SERVER_IP),
                DEFAULT_PORT
        );
        for (int i = 0; i < 101; i++) {
            ds.send(DpSend);
        }
    }


}
