package launchPattern;

import serveur.Game;
import serveur.IContext;
import serveur.IProtocole;

import java.net.Socket;

public class ProtocoleNouveauJoueur implements IProtocole {
    @Override
    public void execute(IContext aContext, Socket s) {
        Game game = (Game) aContext;

        game.addJoueur(s);
    }
}
