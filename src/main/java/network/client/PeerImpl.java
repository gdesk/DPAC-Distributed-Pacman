package network.client;
import java.io.IOException;
import java.net.*;

/**
 * Created by Federica on 11/07/17.
 */

public class PeerImpl implements Peer{

    private final  int id;
    private final int port;
    private String message;

    public PeerImpl(final int id, final int port){
            this.id = id;
            this.port = port;

    }

    public void initServer() throws UnknownHostException {
        InetAddress hostAddress = InetAddress.getLocalHost();

        (new Thread(() -> {
            //trasformo la stringa in byte
            byte data[] = message.getBytes();
            //creo la socket
            DatagramSocket socket = null;

            try {
                socket = new DatagramSocket();
                socket.setBroadcast(true);

            } catch (SocketException ex) {
                ex.printStackTrace();
            }

            /**
             * creo il pacchetto che voglio spedire.
             * NB. port = porta da cui invio i pacchetti
             */

            DatagramPacket packet = new DatagramPacket(data, data.length, hostAddress, port);

            try {
                System.out.println("Peer " + id + " (server) says " + new String(packet.getData()));
                socket.send(packet);
                Thread.sleep(50);

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }

        })).start();
    }

    public void initClient(int serverPort) {
        (new Thread(() -> {

            DatagramSocket socket = null;
            try {
                //mi metto in ascolto sulla porta del server
                socket = new DatagramSocket(serverPort);

            } catch (SocketException ex) {
                ex.printStackTrace();
            }
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            String temp;
            while (!packet.equals(null)) {
                try {
                    socket.receive(packet);
                    temp = new String(packet.getData());
                    System.out.println("Peer " + id + " (client) says " + temp);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        })).start();

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPort() {
        return port;
    }

    //@Override
    /*public void stopServer() {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for(Thread t: threads){
            if(t.getId() == serverThreadId){
                t.interrupt();
            }
        }
    }*/

    //@Override
    /*public void stopClient() {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for(Thread t: threads){
            if(t.getId() == clientThreadId){
                t.interrupt();
            }
        }
    }*/



}
