package it.uniroma3.diadia.comandi;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstarctComando implements Comando {
	abstract public void esegui(Partita partita,IO io);
	public void setParametro(String parametro) {}
	abstract public String getNome();
}
