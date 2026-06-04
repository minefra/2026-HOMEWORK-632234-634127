package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Costanti;

public class Giocatore {
	private int cfu;
	private Borsa borsa=new Borsa();
	
	public Giocatore() {
		this.cfu= Costanti.getCFUIniziali();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	public boolean isVivo() {
		if(this.getCfu()>0)
			return true;
		else
			return false;
	}
}
