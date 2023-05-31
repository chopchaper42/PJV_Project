package network.udp.client;

public class ClientControllerSingleton {
    private static ClientController controller;

    public static ClientController getInstance() {
        return controller;
    }

    public static void setController(ClientController clientController) {
        controller = clientController;
    }
}
