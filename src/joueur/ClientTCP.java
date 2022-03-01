package joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {

	private int numeroPort;

	private String nomServeur;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;	
	
	/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero */
	public ClientTCP(String unNomServeur, int unNumero) {
		numeroPort = unNumero;
		nomServeur = unNomServeur;
	} 

	public boolean connecterAuServeur() {        
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket( nomServeur, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader ( 
					new InputStreamReader (socketServeur.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return ok;
	} 	
	
	public void deconnecterDuServeur() {        
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	} 	
	
	public String transmettreChaine(String uneChaine) {        
		String msgServeur = null;
		try {
			socOut.println( uneChaine );
			socOut.flush();

			msgServeur = socIn.readLine();
			System.out.println("Reponse serveur : " + msgServeur);

		} catch (Exception e) {
			System.err.println("Echec transmettreChaine : " + e);
		}
		return msgServeur;
	}
}
