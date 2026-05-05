package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando  {

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Il comando inserito non esiste!");
	}

	@Override
	public void setParametro(String parametro) {}
	@Override
	public String getNome() {
		return "Comando non valido";
	}
}
