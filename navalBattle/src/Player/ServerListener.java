package Player;

import Communication.CommunicationAPI;
import Messages.Message;
import Messages.UDPMessage;
import Utils.HigherLayer;

/**
 * Class responsible for receiving messages sent by the server
 */
public class ServerListener implements Runnable, HigherLayer {
    // Class this class reports to
    Player superior;

    public ServerListener(Player higherlayer) {
        superior = higherlayer;
    }

    @Override
    public void receiveReport(Message message) {
        if (!(message instanceof UDPMessage))
            System.err.println("Received unexpected type of message");

        // TODO -- Do stuff with received UDPMessage (bubble it up)
        UDPMessage msgUDP = (UDPMessage) message;
        System.out.println(msgUDP.getContent());
    }

    @Override
    public void run() {
        //Starts listening to the respective channel
        CommunicationAPI.channel(this, FIXED_PORT_ACROSS_APP);
    }
}
