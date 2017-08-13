import client.communication.model.ToClientCommunication;
import client.communication.model.ToClientCommunicationImpl;
import client.controller.*;
import client.model.peerCommunication.ClientIncomingMessageHandler;
import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import client.model.peerCommunication.ClientOutcomingMessageHandler;
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl;
import client.view.MainFrame;

import javax.swing.*;

/**
 * Created by ManuBottax on 16/06/2017.
 */
public class HelloPacMan {
    public static void main(String[] args) {

        ControllerCharacter controllerCharacter = BaseControllerCharacter.instance();
        ControllerMatch controllerMatch = BaseControllerMatch.instance();
        ControllerUser controllerUser = BaseControllerUser.instance();

        ToClientCommunication model = new ToClientCommunicationImpl();

        ClientIncomingMessageHandler ci = new ClientIncomingMessageHandlerImpl();
        ClientOutcomingMessageHandler co = new ClientOutcomingMessageHandlerImpl();

        controllerMatch.model(model);
        controllerUser.model(model);

        ci.addObserver(controllerCharacter);
        co.addObserver(controllerMatch);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance();
            }
        });
    }
}
