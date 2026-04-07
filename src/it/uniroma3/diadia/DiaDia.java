package it.uniroma3.diadia;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;


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
	
	private IOConsole io;

	public DiaDia(IOConsole io) {
		
		this.io = io;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione;

	    io.mostraMessaggio(MESSAGGIO_BENVENUTO);

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
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
		    this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
		    this.prendiAttrezzo(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
		    this.posaAttrezzo(comandoDaEseguire.getParametro());
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLab().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLab().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		io.mostraMessaggio(partita.getLab().getStanzaCorrente().getDescrizione());
	}
	
	
	private void prendiAttrezzo(String nome) {
	    if (nome == null) {
	    	io.mostraMessaggio("Cosa vuoi prendere?");
	        return;
	    }

	    Stanza stanza = this.partita.getLab().getStanzaCorrente();
	    Borsa borsa = this.partita.getGiocatore().getBorsa();

	    Attrezzo a = stanza.getAttrezzo(nome);

	    if (a == null) {
	    	io.mostraMessaggio("Attrezzo non presente nella stanza!");
	    } else if (borsa.addAttrezzo(a)) {
	        stanza.removeAttrezzo(a);
	        io.mostraMessaggio("Hai preso " + nome);
	    } else {
	    	io.mostraMessaggio("Borsa piena!");
	    }
	}
	
	private void posaAttrezzo(String nome) {
	    if (nome == null) {
	    	io.mostraMessaggio("Cosa vuoi posare?");
	        return;
	    }

	    Stanza stanza = this.partita.getLab().getStanzaCorrente();
	    Borsa borsa = this.partita.getGiocatore().getBorsa();

	    Attrezzo a = borsa.getAttrezzo(nome);

	    if (a == null) {
	    	io.mostraMessaggio("Non hai questo attrezzo!");
	    } else {
	        stanza.addAttrezzo(a);
	        borsa.removeAttrezzo(nome);
	        io.mostraMessaggio("Hai posato " + nome);
	    }
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
	    DiaDia gioco = new DiaDia(io);
	    gioco.gioca();
			
	}
	
}