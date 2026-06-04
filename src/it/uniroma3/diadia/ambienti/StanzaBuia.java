package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Costanti;

public class StanzaBuia extends Stanza {
	private String attrezzoSpeciale;
	public StanzaBuia(String nome,String attrezzoSpeciale) {
		super(nome);
		this.attrezzoSpeciale=attrezzoSpeciale;
	}
	public StanzaBuia(String nome) {
		this(nome,Costanti.getAttrezzoSpecialeStanzaBuia());
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
