package it.uniroma3.diadia.giocatore;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa=new Borsa();
	
	public Giocatore() {
		this.cfu= CFU_INIZIALI;
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
