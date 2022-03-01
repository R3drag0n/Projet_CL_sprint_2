package launchPattern;

import serveur.Game;
import serveur.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(new Game(), new ProtocoleNouveauJoueur(),6666);
		myServ.start();
		
	}
}
