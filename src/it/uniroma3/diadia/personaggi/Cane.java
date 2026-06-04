package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private static final String MESSAGGIO_INTERAZIONE =Costanti.getMessaggioInterazioneCane();
	private static final String CIBO_PREFERITO=Costanti.getCiboPreferitoCane();
	public Attrezzo oggettoTrattenuto;
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);	
		this.oggettoTrattenuto=new Attrezzo("osso",2);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_INTERAZIONE;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String messaggio=null;
		if(this.oggettoTrattenuto!=null) {
			if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
				partita.getLab().getStanzaCorrente().addAttrezzo(oggettoTrattenuto);
				messaggio="Il cane ha laciato un attrezzo: "+oggettoTrattenuto.toString();
				this.oggettoTrattenuto=null;
			}
			else {
				messaggio="Al cane non piace questo attrezzo! "+agisci(partita);
			}	
		}
		else {
			messaggio="Il cane ha gia ricevuto il regalo"+agisci(partita);
		}
		return messaggio;
	}
}
