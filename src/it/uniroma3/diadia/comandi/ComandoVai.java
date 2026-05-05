package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando  {
	private String direzione;
	@Override
	public void esegui(Partita partita,IO io) {
		Stanza stanzaCorrente=partita.getLab().getStanzaCorrente();
		Stanza prossimaStanza=null;
		if(this.getDirezione()==null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione.");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if(prossimaStanza==null) {
			io.mostraMessaggio("Direzione inesistente.");
			return;
		}
		partita.getLab().setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getLab().getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	@Override
	public void setParametro(String parametro) {
		this.setDirezione(parametro);
	}
	
	public String getDirezione() {
		return direzione;
	}
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	@Override
	public String getNome() {
		return "Vai";
	}
	
}
