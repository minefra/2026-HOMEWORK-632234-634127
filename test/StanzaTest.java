import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	private Stanza stanza;
	private Attrezzo spada;
	@BeforeEach
	public void setUp(){
		this.stanza=new Stanza("Atrio");
		this.spada=new Attrezzo("Spada",3);
		this.stanza.addAttrezzo(spada);
	}

	@Test
	public void Test_impostaStanzaAdiacente_e_getStanzaAdiacente_Uguali_Vero() {
		aggiungiStanzaAdicente();
		assertEquals("Aula11",this.stanza.getStanzaAdiacente("nord").getNome(),"Devono essere uguali perché in direzione nord è presente l'aula11.");
	}
	
	public void aggiungiStanzaAdicente() {
		Stanza aula11=new Stanza("Aula11");
		this.stanza.impostaStanzaAdiacente("nord",aula11);
	}
	
	@Test
	public void Test_getDirezioneAdiacente_Inesistente_Null() {
		assertNull(this.stanza.getStanzaAdiacente("nord"),"Se proviamo a fare una get su una stanza in una direzione che non esiste deve restituire null.");
	}
	


	@Test
	public void Test_impostaStanzaAdiacente_Direzioni_Esaurite_Null() {
		creazioneStanze();
		Stanza s5=new Stanza("D");
		this.stanza.impostaStanzaAdiacente("nord-est", s5);
		assertNull(this.stanza.getStanzaAdiacente("nord-est"),"La seguente stanza non dovrebbe essere stata aggiunta alle direzioni visto che hanno precedentemente raggiunto il loro massimo di istanze.");
	}
	
	public void creazioneStanze() {
		Stanza s1=new Stanza("A");
		Stanza s2=new Stanza("B");
		Stanza s3=new Stanza("C");
		Stanza s4=new Stanza("D");
		
		this.stanza.impostaStanzaAdiacente("nord", s1);
		this.stanza.impostaStanzaAdiacente("est", s2);
		this.stanza.impostaStanzaAdiacente("ovest", s3);
		this.stanza.impostaStanzaAdiacente("sud", s4);
	}
	
	@Test
	public void Test_hasAttrezzo_Vero(){
		assertTrue(this.stanza.hasAttrezzo("Spada"),"Deve ritorna true perchè l'attrezzo cercato è presente nella stanza.");
	}
	
	@Test
	public void Test_hasAttrezzo_Falso(){
		assertFalse(this.stanza.hasAttrezzo("Ascia"),"Deve ritorna false perchè l'attrezzo cercato NON è presente nella stanza.");
		
	}
	
	@Test
	public void Test_getAttrezzo_Presente() {
		assertEquals("Spada",this.stanza.getAttrezzo("Spada").getNome(),"L'attrezzo è presente nella stanza quindi ritorna l' oggetto attrezzo cercato.");	
	}
	@Test
	public void Test_getAttrezzo_Non_presente() {
		assertEquals(null,this.stanza.getAttrezzo("Ascia"),"L'attrezzo non è presente nella stanza quindi il metodo getAttrezzo ritorna null.");	
	}
	
	@Test
	public void Test_removeAtrrezzo_Presente_Vero() {
		assertTrue(this.stanza.removeAttrezzo(this.spada),"Rimuove l'attrezzo passato e ritorna vero se è stato rimosso.");
	}
	
	@Test
	public void Test_removeAtrrezzo_Non_Presente_Falso() {
		Attrezzo ascia=new Attrezzo("acia",3);
		assertFalse(this.stanza.removeAttrezzo(ascia),"Cerca di rimuuove un attrezzo che NON è presente nella stanza.");
		
	}

	

}
