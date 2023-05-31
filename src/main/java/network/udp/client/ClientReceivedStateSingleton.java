package network.udp.client;

public class ClientReceivedStateSingleton
{
    private static ClientReceivedState clientReceivedState;

    public static ClientReceivedState getInstance() {
        if (clientReceivedState == null) {
            clientReceivedState = new ClientReceivedState();
        }
        return clientReceivedState;
    }
}
