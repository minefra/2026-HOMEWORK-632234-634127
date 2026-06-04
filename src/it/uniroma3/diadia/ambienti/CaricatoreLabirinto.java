
package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {
	private LabirintoBuilder builder;
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	private static final String STANZE_SPECIALI_MARKER = "Stanze speciali:";
	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Stanze Speciali: tipo nome se buoia attrezzo, se bloccata direzio attrezzo, se magica niente 
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11
		Personaggi: tipo nome prsentazione stanza se mago attrezzo

	 */
	private LineNumberReader reader;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder=new LabirintoBuilder();
	}
	
	public CaricatoreLabirinto(StringReader stringReader) {
        this.reader = new LineNumberReader(stringReader);
        this.builder=new LabirintoBuilder();
    }

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeSpeciali();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	private void leggiECreaStanzeSpeciali() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_SPECIALI_MARKER);
		for(String stanzaSpeciale : separaStringheAlleVirgole(nomiStanze)) {
			if(stanzaSpeciale.isEmpty()) continue;
			String tipo=null;
			String nomeStanza=null;
			try (Scanner scannerLinea = new Scanner(stanzaSpeciale)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo della stanza."));
				tipo= scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza speciale."+tipo));
				nomeStanza = scannerLinea.next();
				if(tipo.equals("Magica"))
					builder.addStanzaMagica(nomeStanza);
				else if(tipo.equals("Buia")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell attrezzo che illumina la stanza."));
					String attrezzoSpeciale = scannerLinea.next();
					this.builder.addStanzaBuia(nomeStanza, attrezzoSpeciale);
				}
				else if(tipo.equals("Bloccata")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell attrezzo che sblocca la stanza."));
					String direzione = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata"));
					String attrezzoSpeciale = scannerLinea.next();
					this.builder.addStanzaBloccata(nomeStanza,Direzione.valueOf(direzione.toUpperCase()) ,attrezzoSpeciale);
				}
			}
		}

		
	}

	//Personaggi: TIPO nome prsentazione stanza se mago attrezzo peso
	private void leggiECollocaPersonaggi()  throws FormatoFileNonValidoException{
		
		String rigaPersonaggi=this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String personaggi: separaStringheAlleVirgole(rigaPersonaggi)) {
			if (personaggi.isEmpty()) continue;
			String tipoPersonaggio= null;
			String nomePersonaggio = null;
			String presentazione = null; 
			String stanza = null;

			try (Scanner scannerLinea = new Scanner(personaggi)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio."));
				tipoPersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La presentazione del personaggio di nome"+nomePersonaggio));
				presentazione=scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomePersonaggio+"."));
				stanza = scannerLinea.next();
				if(tipoPersonaggio.equals("Strega"))
					this.builder.addPersonaggioStrega(nomePersonaggio, presentazione,stanza);
				if(tipoPersonaggio.equals("Cane"))
					this.builder.addPersonaggioCane(nomePersonaggio, presentazione,stanza);
				if(tipoPersonaggio.equals("Mago")) {
					String attrezzo=null;
					String peso=null;
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio è mago si aspetta un attrezzo."));
					attrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio è un mago si aspetta il peso dell' attrezzo."));
					peso = scannerLinea.next();
					this.builder.addPersonaggioMago(nomePersonaggio,presentazione,stanza,attrezzo,peso);
				}
					
			}		
		}	
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga!=null && riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next().trim());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}
	
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			if (specificaAttrezzo.isEmpty()) continue;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			 peso = Integer.parseInt(pesoAttrezzo);

	        this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
	    
	    // 1. Prima separiamo le varie uscite usando la virgola
	    for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
	        if (specificaUscita.isEmpty()) continue; // Salta se non ci sono uscite
	        
	        // 2. Poi analizziamo ogni singola uscita (es. "biblioteca nord N10")
	        try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {           
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
	            String stanzaPartenza = scannerDiLinea.next();
	            
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione di una uscita"));
	            String dir = scannerDiLinea.next();
	            
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la destinazione di una uscita"));
	            String stanzaDestinazione = scannerDiLinea.next();
	        
	            impostaUscita(stanzaPartenza, Direzione.valueOf(dir.toUpperCase()), stanzaDestinazione);
	        }
	    }
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.builder.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.builder.getStanzaVincente();
	}
	public Labirinto getLabirinto() {
	    return this.builder.getLabirinto();
	}
}