package launchPattern;

import joueur.Joueur;
import joueur.JoueurGUI;

public class MainJoueur2 {

	public static void main(String[] args) {
		Joueur joueur1 = new Joueur("2");
		JoueurGUI monJoueurGUI = new JoueurGUI(joueur1);
		monJoueurGUI.initGUI();
	}

}
