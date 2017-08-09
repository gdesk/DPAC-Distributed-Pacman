import client.communication.model.ToClientCommunicationImpl;
import client.controller.BaseControllerMatch;
import client.controller.BaseControllerUser;
import client.view.MainFrame;

import javax.swing.*;

/**
 * Created by ManuBottax on 16/06/2017.
 */
public class HelloPacMan {

    public static void main(String[] args) {
        ToClientCommunicationImpl clientComm = new ToClientCommunicationImpl();
        BaseControllerUser.instance().model_$eq(clientComm);
        BaseControllerMatch.instance().model_$eq(clientComm);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance();
            }
        });
    }
}
