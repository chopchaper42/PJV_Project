package network.udp.client;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import Logs.*;
import network.udp.Socket;
import Utility.ConsoleWriter;

public class ClientController/* extends Thread*/
{
    private Socket clientSocket;

    public ClientController(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    /**
     * Runs the client
     */
    public void run()
    {
        var clientReceivedState = ClientReceivedStateSingleton.getInstance();

        while(true)
        {
            byte[] data = clientSocket.listen().getData();
            clientReceivedState.enqueue(data);
//            System.out.println("Client received: " + new String(data, StandardCharsets.UTF_8));
//            System.out.println("ClientReceivedState size: " + clientReceivedState.size());

//            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
//            long threadId = Thread.currentThread().getId();
//            System.out.println("Current Thread ID Controller within ClientController.run(): " + threadId);
//            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }


        }
    }
//
    public void send(String jsonProperty, double x, double y)
    {
        var newState = new UpdatedState(jsonProperty, x, y);
        String newStateJSON = JSONManager.convertObjectToJson(newState);
        // send the new state to the server
        clientSocket.send(newStateJSON);
    }

    public void send(String jsonProperty, double x, double y, double dx, double dy)
    {
        var newState = new UpdatedState(jsonProperty, x, y, dx, dy);
        String newStateJSON = JSONManager.convertObjectToJson(newState);
        System.out.println("---------------Bullet new state -------------");
        System.out.println(newStateJSON);
        System.out.println("---------------------------------------------");
        // send the new state to the server
        clientSocket.send(newStateJSON);
    }

    public UpdatedState checkUpdatesFromAnotherClient()
    {
        var queue = ClientReceivedStateSingleton.getInstance();
        if (queue.isEmpty())
        {
            return null;
        }

        var newStateJson = queue.dequeue();
//        ConsoleWriter.write("queue size: " + queue.size());
//        ConsoleWriter.write("newStateJson:" + newStateJson);
//        System.out.println(newStateJson);
        return JSONManager.convertJsonToObject(newStateJson);
    }
}
