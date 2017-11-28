package client;
import java.util.Date;
import autorizzatore.Autorizzazione;

public interface IntAdmin {
	 int creaRisorsa();
	 void modificaRisorsa();
	 void cancellaRisorsa();
	 Autorizzazione creaAutorizzazione(String _nomeUtente, int _livello, Date _scadenza);
	 boolean revocaAutorizzazione(String chiave);
}