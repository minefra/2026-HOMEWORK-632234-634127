package it.uniroma3.diadia.personaggi;

import java.util.Map;
import java.util.Map.Entry;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_PERMALOSO =Costanti.getMessaggioPermalosoStrega();
	private static final String MESSAGGIO_BUONO =Costanti.getMessaggioBuonoStrega() ;
	private Attrezzo attrezzoRicevuto;
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza stanzaPiuAttrezzi=null;
		Stanza stanzaMenoAttrezzi=null;
		Map <Direzione,Stanza> mappaAdiacenza=partita.getLab().getStanzaCorrente().getMapStanzeAdiacenti();
		if(!mappaAdiacenza.isEmpty()) {
			boolean primo=true;
			for (Entry<Direzione, Stanza> e : mappaAdiacenza.entrySet()) {
				if(primo) {
					stanzaMenoAttrezzi=e.getValue();
					stanzaPiuAttrezzi=e.getValue();
					primo=false;
				}
				else {
					if(stanzaMenoAttrezzi.getAttrezzi().size()>e.getValue().getAttrezzi().size()) {
						stanzaMenoAttrezzi=e.getValue();
					}
					if(stanzaPiuAttrezzi.getAttrezzi().size()<e.getValue().getAttrezzi().size()) {
						stanzaPiuAttrezzi=e.getValue();
					}
				}
			}	
		}
		if(super.haSalutato()) {
			partita.getLab().setStanzaCorrente(stanzaPiuAttrezzi);
			return MESSAGGIO_BUONO;
		}
		else {
			partita.getLab().setStanzaCorrente(stanzaMenoAttrezzi);
			return MESSAGGIO_PERMALOSO;
		}

		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		setAttrezzoRicevuto(attrezzo);
		return "AH AH AH AH AH";
	}

	public Attrezzo getAttrezzoRicevuto() {
		return attrezzoRicevuto;
	}

	public void setAttrezzoRicevuto(Attrezzo attrezzoRicevuto) {
		this.attrezzoRicevuto = attrezzoRicevuto;
	}

}
