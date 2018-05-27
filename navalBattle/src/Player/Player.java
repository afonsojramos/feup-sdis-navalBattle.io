package Player;

import Messages.UDPMessage;
import Utils.Pair;
import Utils.ThreadPool;
import GameLogic.PlayerLogic;

import java.util.HashMap;
import java.util.concurrent.*;

public class Player {

    private ServerSender sender;
    private ServerListener listener;
    private ThreadPool threadPool;
    private PlayerLogic game;

    public Player(String serverIP, String serverPort) {
        threadPool = new ThreadPool();
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
        game = new PlayerLogic(this);
        
        run();
    }

    private void run() {
        threadPool.run(listener);
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYER
     */

    // Method to be called by logic to make requests to the Server
    public int sendServer(HashMap<String, String> params, Pair<String, String> route) {
        // TODO posso fazer aqui a cena de repetir três vezes até mandar
        Future result = threadPool.run(() -> sender.sendRequest(route, params));
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Result of bubbling up functions
    public void receiveReport(UDPMessage clientMessage) {
        reportToLogic(clientMessage.getContent());
    }

    // No need for threadPool as the threadPool was already launched in UDP level
    public void reportToLogic(String updatedMap) {
        game.updateMap(updatedMap);
    }

}