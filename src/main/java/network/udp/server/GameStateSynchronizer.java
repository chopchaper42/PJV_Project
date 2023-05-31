//package network.udp.server;
//
//public class GameStateSynchronizer
//{
//    private final OurServerSocket ourServerSocket;
//
//    public GameStateSynchronizer(OurServerSocket ourServerSocket)
//    {
//        this.ourServerSocket = ourServerSocket;
//    }
//
//    public void synchronizeGameBetweenClients(String gameState, int clientMessageFromID)
//    {
//        var targets = ourServerSocket.getTargets();
//        for (int i = 0; i < targets.length; i++)
//        {
//            if (i == clientMessageFromID)
//                continue;
//            ourServerSocket.send(gameState, targets[i]);
//        }
//    }
//
//    public void endTheGame()
//    {
//        synchronizeGameBetweenClients("game over", 0);
//        synchronizeGameBetweenClients("game over", 1);
//        ourServerSocket.close();
//    }
//}
