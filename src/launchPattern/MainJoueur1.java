package launchPattern;

import joueur.Joueur;
import joueur.JoueurGUI;

public class MainJoueur1 {

	public static void main(String[] args) {
		Joueur joueur1 = new Joueur("Louis");
		JoueurGUI monJoueurGUI = new JoueurGUI(joueur1);
		monJoueurGUI.initGUI();
	}

}
