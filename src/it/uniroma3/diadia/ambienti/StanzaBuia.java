package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private static final String ATTREZZOSPECIALE="lanterna";
	private String attrezzoSpeciale;
	public StanzaBuia(String nome,String attrezzoSpeciale) {
		super(nome);
		this.attrezzoSpeciale=attrezzoSpeciale;
	}
	public StanzaBuia(String nome) {
		this(nome,ATTREZZOSPECIALE);
	}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(this.getAttrezzoSpeciale())) {
			return super.getDescrizione();
		}
		else {
			return "Qui c'è buio pesto";
		}
	}
	
	public String getAttrezzoSpeciale() {
		return this.attrezzoSpeciale;
	}
	
	public void setAttrezzoSpeciale(String attrezzo) {
		this.attrezzoSpeciale=attrezzo;
	}
	
	
}
