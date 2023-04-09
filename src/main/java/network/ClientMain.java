package network;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain
{
    public static final int DEFAULT_PORT = 10422;

    public static void main(String[] args)
    {
        ClientTCP client = new ClientTCP();
        Scanner scr = new Scanner(System.in);
        String ip = getIP(scr);
        boolean connection = client.startConnection(ip, DEFAULT_PORT);

        if (connection)
        {
            String message = "";
            while (!message.equals("end")) {
                System.out.println("Enter your message:");
                message = scr.nextLine();
                try
                {
                    client.sendMessage(message);
                }
                catch (IOException e)
                {
                    System.out.println("Something went wrong!\n" + e.getMessage());
                }
            }
        }
        else
        {
            System.out.println("Connection failed");
        }

    }

    private static String getIP(Scanner scr) {
        System.out.println("Enter server ip:");
        return scr.nextLine();
    }
}
