package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstarctComando{

	@Override
	public void esegui(Partita partita, IO io) {
		String messaggio=null;
		AbstractPersonaggio personaggio=partita.getLab().getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			io.mostraMessaggio("Quale attrezzo vuoi regalare? ");
			io.mostraMessaggio("Ecco gli oggetti presenti nella borsa: "+partita.getGiocatore().getBorsa().toString());
			String nomeAttrezzo=io.leggiRiga();
			Attrezzo attrezzo=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzo!=null) {
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				messaggio=personaggio.riceviRegalo(attrezzo,partita);
			}
			else {
				messaggio="Non possiedi l'oggetto che vuoi regalare";
			}	
		}
		else {
			messaggio="Non puoi regalare nessuno oggetto, perche non è presente nessun personaggio";
		}
		io.mostraMessaggio(messaggio);
	}

	@Override
	public String getNome() {
		return "Regala";
	}

}
