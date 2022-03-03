package serveur;

import joueur.JoueurID;
import launchPattern.ProtocoleManche5J;

import java.net.Socket;

public class ProcessusManche5J extends Thread {
    private JoueurID myJ;

    public  ProcessusManche5J(JoueurID j) {
        super("JoueurThread");
        myJ = j;
    }

    public void run() {
        try {

            ProtocoleManche5J p = new ProtocoleManche5J();
            p.execute(myJ);

        } catch (Exception e) {
            System.err.println("[ProcessusTransaction] Exception : " + e );
            e.printStackTrace();
        }
    }
}
