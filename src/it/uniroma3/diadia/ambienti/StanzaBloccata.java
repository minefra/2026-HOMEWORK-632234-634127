package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Costanti;

public class StanzaBloccata extends Stanza{

	private String attrezzoSpeciale;
	private Direzione direzioneBloccata;
	public StanzaBloccata(String nome,Direzione direzione,String attrezzoSpeciale) {
		super(nome);
		this.attrezzoSpeciale=attrezzoSpeciale;
		this.direzioneBloccata=direzione;
	}
	public StanzaBloccata(String nome,Direzione direzione) {
		this(nome,direzione,Costanti.getAttrezzoSpecialeStanzaBloccata());
	}
	public String getAttrezzoSpeciale() {
		return this.attrezzoSpeciale;
	}
	public void setAttrezzoSpeciale(String attrezzo) {
		this.attrezzoSpeciale=attrezzo;
	}
	
	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	public void setDirezioneBloccata(Direzione direzione) {
		this.direzioneBloccata=direzione;
	}
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(getDirezioneBloccata().equals(direzione)) {
			if(super.hasAttrezzo(attrezzoSpeciale)) {
				return super.getStanzaAdiacente(direzione);
			}
			else{
			return this;
			}	
		}
			return super.getStanzaAdiacente(direzione);
		
	}
	@Override
	public String getDescrizione() {
		return super.getDescrizione()+"\nLa stanza in direzione "+getDirezioneBloccata()+" è bloccata, per sbloccarla usa l'attrezzo "+getAttrezzoSpeciale();
	}
	
}

	

