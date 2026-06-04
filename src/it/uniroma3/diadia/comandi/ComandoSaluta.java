package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstarctComando{
	private static final String MESSAGGIO_NON_SALUTO ="Non hai salutato nessuno...";
	private String messaggio;
	
	
	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio p=partita.getLab().getStanzaCorrente().getPersonaggio();
		if(p!=null) {
			this.messaggio=p.saluta();
		}
		else {
			this.messaggio=MESSAGGIO_NON_SALUTO;
		}
		io.mostraMessaggio(messaggio);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}

	@Override
	public String getNome() {
		return "Saluta";
	}

}
