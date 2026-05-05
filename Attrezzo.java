package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private static final String ATTREZZOSPECIALE="chiave";
	private String attrezzoSpeciale;
	private String direzioneBloccata;
	public StanzaBloccata(String nome,String direzione,String attrezzoSpeciale) {
		super(nome);
		this.attrezzoSpeciale=attrezzoSpeciale;
		this.direzioneBloccata=direzione;
	}
	public StanzaBloccata(String nome,String direzione) {
		this(nome,direzione,ATTREZZOSPECIALE);
	}
	public String getAttrezzoSpeciale() {
		return this.attrezzoSpeciale;
	}
	public void setAttrezzoSpeciale(String attrezzo) {
		this.attrezzoSpeciale=attrezzo;
	}
	
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	public void setDirezioneBloccata(String direzione) {
		this.direzioneBloccata=direzione;
	}
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(getDirezioneBloccata().equals(direzione)) {
			if(super.hasAttrezzo(attrezzoSpeciale)) {
				return super.getStanzaAdiacente(direzione);
			}
			else{
			return this;
			}
			
		}
		else {
			return super.getStanzaAdiacente(direzione);
		}
	}
	@Override
	public String getDescrizione() {
		return super.getDescrizione()+"\nLa stanza in direzione "+getDirezioneBloccata()+" è bloccata, per sbloccarla usa l'attrezzo "+getAttrezzoSpeciale();
	}
	
}

	

