import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	public ComandoPrendi com;
	public IOConsole io;
	@BeforeEach
	public void  setUp() {
		this.com=new ComandoPrendi();
		this.io=new IOConsole();
	}
	
	@Test
	public void Test_setParametro_Vero() {
		String osso="Osso";
		com.setParametro(osso);
		assertTrue(osso.equals(com.getParametro()),"I due oggetti stringa devono essere identici.");
	}
	@Test
	public void Test_getParametro_Nullo_Vero() {
		assertNull(com.getParametro(),"Se all'oggetto non viene passato nessun parametro tramite il metodo setParametro, allora il parametro deve essere nullo.");
	}
	@Test
	public void Test_getParametro_Non_Nullo_Vero() {
		com.setParametro("osso");
		assertNotNull(com.getParametro(),"Se all'oggetto viene passato un parametro tramite il metodo setParametro, allora il parametro deve ritornare la stringa del parametro.");
	}
	
	@Test
	public void Test_esegui_Attrezzo_Rimosso_Dalla_Stanza_Falso() {
		Partita p=iniziaPartita();
		this.com.setParametro("Osso");
		this.com.esegui(p, io);
		assertFalse(p.getLab().getStanzaCorrente().hasAttrezzo("Osso"),"Se il giocatore prende un oggetto dalla stanza allora deve essere rimosso dalla stanza stessa.");
		
	}
	@Test
	public void Test_esegui_Attrezzo_Aggiunto_alla_Borsa_Vero() {
		Partita p=iniziaPartita();
		this.com.setParametro("Osso");
		this.com.esegui(p, io);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("Osso"),"Se il giocatore prende l'oggetto dalla stanza deve essere inserito nella borsa");
		
	}
	

	public Partita iniziaPartita() {
		Partita p=new Partita();
		Attrezzo osso=new Attrezzo("Osso",2);
		p.getLab().getStanzaCorrente().addAttrezzo(osso);
		return p;
	}
	

}
