package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	private String nome;
    private List<Attrezzo> attrezzi;
    private int numeroDirezioni;
    private Map<Direzione,Stanza> stanzeAdiacenti;
    private AbstractPersonaggio personaggio;
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti=new HashMap<Direzione,Stanza>();       
        this.attrezzi =new ArrayList <Attrezzo>();
        this.setNumeroDirezioni(0);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {		
    	if(this.numeroDirezioni<4) {
    		 this.stanzeAdiacenti.put(direzione,stanza);
    		 this.numeroDirezioni++;
    	}
       
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(!this.hasAttrezzo(attrezzo.getNome()))
    			return this.attrezzi.add(attrezzo);
    	else return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	Set <Direzione> direzioni=new HashSet<>();
    	risultato.append("Ti trovi nella stanza: "+this.nome);
    	risultato.append("\nUscite: ");
    	direzioni=this.stanzeAdiacenti.keySet();
    	for(Direzione a: direzioni) {
    		risultato.append(a+" ");
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null) {
        		risultato.append(attrezzo.toString()+" ");
    		}

    	}
    	
    	if(getPersonaggio()!=null) risultato.append("\nPersonaggi presenti: "+getPersonaggio().toString());
    	else risultato.append("\nPersonaggi presenti: nessuno");
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> i=this.attrezzi.iterator();
		while(i.hasNext()) {
			Attrezzo a=i.next();
			if(a.getNome().equals(nomeAttrezzo)) return a;
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}


	public Set<Direzione> getDirezioni() {
		Set<Direzione> direzioni = new HashSet<>();
		direzioni=this.stanzeAdiacenti.keySet();
	    return direzioni;
    }

	public int getNumeroDirezioni() {
		return numeroDirezioni;
	}

	public void setNumeroDirezioni(int numeroDirezioni) {
		this.numeroDirezioni = numeroDirezioni;
	}

	public Map<Direzione,Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	@Override
	public boolean equals(Object o) {
		if(this==o)return true;
		if(o.getClass()!=this.getClass())return false;
		Stanza that=(Stanza)o;
		return this.getNome().equals(that.getNome());
		
	}

	public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	


}