package serveur;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement ServeurSpecifique)
 */
class ProcessusTransaction extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public  ProcessusTransaction(Socket uneSocket, ServeurTCP unServeur) {        
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusTransaction] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {

			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket);

		} catch (Exception e) {
			System.err.println("[ProcessusTransaction] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
