package network;

import java.io.*;
import java.net.*;

public class ClientTCP
{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public boolean startConnection(String ip, int port)
    {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void sendMessage(String msg) throws IOException
    {
        out.println(msg);
        /*String resp = in.readLine();
        return resp;*/
    }

    public void stopConnection() throws IOException
    {
        in.close();
        out.close();
        clientSocket.close();
    }
}
