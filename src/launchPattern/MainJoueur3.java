package launchPattern;

import joueur.Joueur;
import joueur.JoueurGUI;

public class MainJoueur3 {

	public static void main(String[] args) {
		Joueur joueur1 = new Joueur("3");
		JoueurGUI monJoueurGUI = new JoueurGUI(joueur1);
		monJoueurGUI.initGUI();
	}

}
