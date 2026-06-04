package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.HashMap;

import java.util.Map;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
	public Labirinto() {
		
	}
	public Labirinto(Stanza inizio,Stanza vincitrice){
		this.stanzaCorrente=inizio;
		this.stanzaIniziale=inizio;
		this.stanzaVincente=vincitrice;
	}
	public Labirinto(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		CaricatoreLabirinto c =new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
		this.stanzaCorrente = this.stanzaIniziale;
		}
	 public void creaStanze() {
		// 1. Inizializziamo il builder e creiamo la struttura del labirinto
			LabirintoBuilder builder = new LabirintoBuilder()
					
					// Crea l'Atrio (StanzaBloccata) e aggiunge l'osso (addAttrezzo agisce sull'ultima stanza aggiunta)
					.addStanzaBloccata("Atrio", Direzione.NORD, "chiave")
					.addAttrezzo("osso", 1)
					
					// Crea la Biblioteca (StanzaVincente)
					.addStanzaVincente("Biblioteca")
					
					// Crea l'Aula N11 (StanzaMagica con soglia 3)
					.addStanzaMagica("Aula N11", 3)
					.addPersonaggioMago("Merlino", "Sono un mago", "spada", 5)
					// Crea l'Aula N10 e aggiunge la lanterna
					.addStanza("Aula N10")
					.addAttrezzo("lanterna", 3)
					.addAttrezzo("croccantini",3)
					.addPersonaggioStrega("Marla", "Sono una strega un po malvaggia")
					// Crea il Laboratorio (StanzaBuia dove serve la lanterna) e aggiunge la chiave
					.addStanzaBuia("Laboratorio Campus", "lanterna")
					
					.addAttrezzo("chiave", 5)
					.addPersonaggioCane("Jonny", "woof woof!")
					// 2. Definiamo tutti i collegamenti (adiacenze) tra le stanze
					.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
					.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
					.addAdiacenza("Atrio", "Aula N10", Direzione.SUD)
					.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
					
					.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST)
					.addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
					
					.addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
					.addAdiacenza("Aula N10", "Aula N11",Direzione.EST)
					.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST)
					
					.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.EST)
					.addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.OVEST)
					
					.addAdiacenza("Biblioteca", "Atrio", Direzione.SUD);

			// Gestione specifica per l'Atrio come stanza iniziale (essendo una StanzaBloccata)
			Stanza atrio = builder.getListaStanze().get("Atrio");
			builder.getLabirinto().addStanzaIniziale(atrio);
			builder.getLabirinto().setStanzaCorrente(atrio);

			// Copiamo i riferimenti configurati dal builder all'interno di questo oggetto Labirinto corrente
			this.stanzaCorrente = builder.getLabirinto().getStanzaCorrente();
			this.stanzaIniziale = builder.getLabirinto().getStanzaIniziale();
			this.stanzaVincente = builder.getLabirinto().getStanzaVincente();
	    }

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void addStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map <String,Stanza> mappa;
		
		public LabirintoBuilder() {
	        this.labirinto = new Labirinto();
	        this.mappa = new HashMap<>();
	    }
		
		public Stanza getStanzaVincente() {
			return labirinto.getStanzaVincente();
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			Stanza stanzaVincente=new Stanza(nomeStanzaVincente);
			this.labirinto.setStanzaVincente(stanzaVincente);
			this.aggiornaMappaEStanza(stanzaVincente);
			return this;
		}
		
		public Stanza getStanzaIniziale() {
			return labirinto.getStanzaIniziale();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			Stanza stanzaIniziale=new Stanza(nomeStanzaIniziale);
			this.labirinto.addStanzaIniziale(stanzaIniziale); 
			this.labirinto.setStanzaCorrente(stanzaIniziale);
			this.aggiornaMappaEStanza(stanzaIniziale);
			return this;
		
		}
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int i) {
			if(this.ultimaStanzaAggiunta!=null) {
				Attrezzo nuovoAttrezzo=new Attrezzo(nomeAttrezzo,i);
				this.ultimaStanzaAggiunta.addAttrezzo(nuovoAttrezzo);
			}
			return this;
		}
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
		    Stanza stanza = this.mappa.get(nomeStanza);
		    if (stanza != null) {
		        Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzo, peso);
		        stanza.addAttrezzo(nuovoAttrezzo);
		    }
		    return this;
		}
		
		public Stanza getUltimaStanzaAggiunta() {
			return this.ultimaStanzaAggiunta;
		}
		
		public LabirintoBuilder setUltimaStanzaAggiunta(Stanza ultimaStanzaAggiunta) {
			this.ultimaStanzaAggiunta = ultimaStanzaAggiunta;
			return this;
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza nuovaStanza=new Stanza(nomeStanza);
			this.aggiornaMappaEStanza(nuovaStanza);
			return this;
		}
		
		private void aggiornaMappaEStanza(Stanza stanza) {
			this.mappa.put(stanza.getNome(),stanza);
			this.ultimaStanzaAggiunta=stanza;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			StanzaMagica nuovaStanza=new StanzaMagica(nomeStanzaMagica,sogliaMagica);
			this.aggiornaMappaEStanza(nuovaStanza);
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica) {
			StanzaMagica nuovaStanza=new StanzaMagica(nomeStanzaMagica);
			this.aggiornaMappaEStanza(nuovaStanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzione, String nomeAttrezzo) {
			StanzaBloccata nuovaStanza=new StanzaBloccata(nomeStanza,direzione,nomeAttrezzo);
			this.aggiornaMappaEStanza(nuovaStanza);
			return this;
		}	
		
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzo) {
			StanzaBuia nuovaStanza=new StanzaBuia(nomeStanza,nomeAttrezzo);
			this.aggiornaMappaEStanza(nuovaStanza);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaDiPartenza, String nomeStanzaDiArrivo, Direzione direzione) {
			Stanza partenza=this.mappa.get(nomeStanzaDiPartenza);
			Stanza arrivo=this.mappa.get(nomeStanzaDiArrivo);
			if(partenza!=null&&arrivo!=null) {
				partenza.impostaStanzaAdiacente(direzione, arrivo);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggioMago(String nome,String presentazione,String nomeAttrezzo,int pesoAttrezzo) {
			if(this.ultimaStanzaAggiunta!=null) {
				Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,pesoAttrezzo);
				AbstractPersonaggio personaggio=new Mago(nome,presentazione,attrezzo);
				this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggioStrega(String nome,String presentazione) {
			if(this.ultimaStanzaAggiunta!=null) {
				AbstractPersonaggio personaggio=new Strega(nome,presentazione);
				this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggioCane(String nome,String presentazione) {
			if(this.ultimaStanzaAggiunta!=null) {
				AbstractPersonaggio personaggio=new Cane(nome,presentazione);
				this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
			}
			return this;
		}
		
		public Map<String,Stanza> getListaStanze() {
			return this.mappa;
		}

		public LabirintoBuilder addPersonaggioStrega(String nomePersonaggio, String presentazione, String stanza) {
			Stanza stanzaDaPosizionare=this.mappa.get(stanza);
			if(stanzaDaPosizionare!=null) {
				AbstractPersonaggio personaggio=new Strega(nomePersonaggio,presentazione);
				stanzaDaPosizionare.setPersonaggio(personaggio);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggioCane(String nomePersonaggio, String presentazione, String stanza) {
			Stanza stanzaDaPosizionare=this.mappa.get(stanza);
			if(stanzaDaPosizionare!=null) {
				AbstractPersonaggio personaggio=new Cane(nomePersonaggio,presentazione);
				stanzaDaPosizionare.setPersonaggio(personaggio);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggioMago(String nomePersonaggio, String presentazione, String stanza,String NomeAttrezzo,String pesoAttrezzo) {
			Stanza stanzaDaPosizionare=this.mappa.get(stanza);
			if(stanzaDaPosizionare!=null) {
				int peso = Integer.parseInt(pesoAttrezzo);
				Attrezzo attrezzo=new Attrezzo(NomeAttrezzo,peso);
				AbstractPersonaggio personaggio=new Mago(nomePersonaggio,presentazione,attrezzo);
				stanzaDaPosizionare.setPersonaggio(personaggio);
			}
			return this;
		}
		

		
		
		

	}
}
