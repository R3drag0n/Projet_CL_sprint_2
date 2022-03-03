package launchPattern;

import joueur.JoueurID;
import serveur.Game;
import serveur.IContext;
import serveur.IProtocole;

import java.net.Socket;

public class ProtocoleManche5J {
    public void execute(JoueurID j) {
        j.uneManche(j);
    }
}
