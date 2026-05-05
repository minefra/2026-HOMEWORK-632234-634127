import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {
	private ComandoPosa com;
	private IO io;
	@BeforeEach
	public void setUp() {
		this.com=new ComandoPosa();
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
	public void Test_esegui_Controllo_se_Oggetto_Aggiunto_Nella_Stanza_Vero() {
		Partita p=this.iniziaPartita();
		com.setParametro("Osso");
		com.esegui(p, this.io);
		assertTrue(p.getLab().getStanzaCorrente().hasAttrezzo("Osso"),"se l'attrezzo è stato posato si deve trovare nella stanza corrente.");
	}
	@Test
	public void Test_esegui_Controllo_se_Oggetto_Posato_Rimosso_Dalla_Borsa_Falso() {
		Partita p=this.iniziaPartita();
		com.setParametro("Osso");
		com.esegui(p, this.io);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("Osso"),"se l'attrezzo è stato posato si deve togliere dalla borsa.");
	}
	
	public Partita iniziaPartita() {
		Partita p=new Partita();
		Attrezzo osso=new Attrezzo("Osso",2);
		p.getGiocatore().getBorsa().addAttrezzo(osso);
		return p;
	}
	

}
