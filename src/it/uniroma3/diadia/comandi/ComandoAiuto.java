package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando  {
	static final private String[] elencoComandi = {"vai+(direzione)", "prendi+(nome attrezzo)", "posa+(nome attrezzo)", "aiuto", "fine","guarda"};
	@Override
	public void esegui(Partita partita,IO io) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
		
	}
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome(){
		return "Aiuto";
	}

}
