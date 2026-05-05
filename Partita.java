package it.uniroma3.diadia;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabricaDiComandi;
import it.uniroma3.diadia.comandi.FabricaDiComandiFisarmonica;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai+(direzione)", "prendi+(nome attrezzo)", "posa+(nome attrezzo)", "aiuto", "fine"};

	private Partita partita;
	
	private IO io;

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione;

	    io.mostraMessaggio(MESSAGGIO_BENVENUTO);
	    io.mostraMessaggio(partita.getLab().getStanzaCorrente().getDescrizione());

	    do {
	        do {
	            istruzione = io.leggiRiga();
	            if (istruzione == null || istruzione.isEmpty()) {
	                io.mostraMessaggio("Nessun comando inserito, riprova:");
	            }
	        } while (istruzione == null || istruzione.isEmpty());
	    } while (!processaIstruzione(istruzione));
	  
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabricaDiComandi factory=new FabricaDiComandiFisarmonica();
		comandoDaEseguire=factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(partita,this.io);
		if(this.partita.vinta())
			io.mostraMessaggio("Hai vinto!!");
		if(!this.partita.getGiocatore().isVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	
	}   

	public static void main(String[] argc) {
		IO io = new IOConsole();
	    DiaDia gioco = new DiaDia(io);
	    gioco.gioca();
			
	}
	
}