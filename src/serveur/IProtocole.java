package serveur;
import java.net.Socket;

public interface IProtocole {

	public void execute(IContext aContext , Socket s);
	
}
