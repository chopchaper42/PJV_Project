package network;

import java.io.*;
import java.net.*;

public class ServerTCP
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean talking = false;

    public boolean openConnectionOnPort(int port)
    {
        try
        {
            System.out.println("trying");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void listen() throws IOException
    {
        String message = "";
        while (!message.equals("stop")) {
            message = in.readLine();
            System.out.println(message);
        }
    }

    private void talk() throws IOException
    {

        while (talking)
        {

        }
        String greeting = in.readLine();
        if ("hello server".equals(greeting))
        {
            out.println("hello client");
        }
        else
        {
            out.println("unrecognised greeting");
        }
    }

    public void stop() throws IOException
    {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
