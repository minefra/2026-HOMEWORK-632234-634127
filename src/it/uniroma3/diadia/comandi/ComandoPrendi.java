package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando  {
	public String nomeAttrezzo;
	@Override
	public void esegui(Partita partita, IO io) {
		 if (getParametro() == null) {
		    	io.mostraMessaggio("Cosa vuoi prendere?");
		        return;
		    }

		    Stanza stanza = partita.getLab().getStanzaCorrente();
		    Borsa borsa = partita.getGiocatore().getBorsa();

		    Attrezzo a = stanza.getAttrezzo(getParametro());

		    if (a == null) {
		    	io.mostraMessaggio("Attrezzo non presente nella stanza!");
		    } else if (borsa.addAttrezzo(a)) {
		        stanza.removeAttrezzo(a);
		        io.mostraMessaggio("Hai preso " + getParametro());
		    } else {
		    	io.mostraMessaggio("Borsa piena!");
		    }
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}
	
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	@Override
	public String getNome() {
		return "Prendi";
	}
}
