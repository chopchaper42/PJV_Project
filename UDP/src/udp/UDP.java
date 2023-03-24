package udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;



public class UDP
{
    public static final int DEFAULT_PORT = 10422;
    public static final String SERVER_IP = "172.26.112.1";

    public static void send() throws IOException
    {
        Scanner sc = new Scanner(System.in);

//        Scanner scr = new Scanner(System.in);
//        String ip = getIP(scr)
//        InetAddress ip = new InetAddress(SERVER_IP);

        byte buff[] = null;

//        while (true)
//        {
        String inp = sc.next();

        buff = inp.getBytes();




//        System.out.println("InetAddress.getByName" + InetAddress.getByName(SERVER_IP));
//        System.out.println("getInetAddress:" + ds.getInetAddress());
//        System.out.println("getLocalAddress:" + ds.getLocalAddress());
        while (true)
        {
            DatagramSocket ds = new DatagramSocket(DEFAULT_PORT);

            DatagramPacket DpSend = new DatagramPacket(
                    buff,
                    buff.length,
                    InetAddress.getByName(SERVER_IP),
                    DEFAULT_PORT
            );
            ds.send(DpSend);
            ds.close();
        }
//        }
    }
}
