package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando  {

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("La partita è terminata: "+partita.isFinita());
		io.mostraMessaggio("CFU attuali: "+partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getLab().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
	}

	@Override
	public void setParametro(String parametro) {}
	@Override
	public String getNome() {
		return "Guarda";
	}
}
