import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	private Borsa borsa;
	Attrezzo spada;
	Attrezzo osso;

	@BeforeEach
	public void setUp() {
		this.borsa=new Borsa();
	}
	
	public void aggiungiOggettiBorsa() {
		this.spada=new Attrezzo("Spada",3);
		this.osso=new Attrezzo("Osso",2);
		this.borsa.addAttrezzo(spada);	
		this.borsa.addAttrezzo(osso);	
	}
	@Test
	public void Test_getPesoMax_Default() {
		assertEquals(10,this.borsa.getPesoMax(),"Il peso di default, se non passato al costruttore deve essere impostato a 10");
	}
	@Test
	public void Test_isEmpty_Vero() {
		assertTrue(this.borsa.isEmpty(),"Deve restituire Vero se non ci sono ogetti presenti nella borsa.");
	}
	
	@Test
	public void Test_isEmpty_Falso() {
		aggiungiOggettiBorsa();
		assertFalse(this.borsa.isEmpty(),"Deve restituire Falso se ci sono ogetti presenti nella borsa");
	}
	
	@Test
	public void Test_hasAttrezzo_Vero() {
		aggiungiOggettiBorsa();
		assertTrue(this.borsa.hasAttrezzo("Spada"),"Deve restituire Vero se è presente un oggetto che ha come nome la stringa passata al metodo come parametro, in questo caso Vero.");
	}
	
	@Test
	public void Test_hasAttrezzo_Falso() {
		assertFalse(this.borsa.hasAttrezzo("Ascia"),"Deve restituire Falso se NON è presente un oggetto che ha come nome la stringa passata al metodo come parametro, in questo caso Falso.");
	}
	
	@Test
	public void Test_getAttrezzo_Esistente() {
		aggiungiOggettiBorsa();
		assertEquals(this.spada,this.borsa.getAttrezzo("Spada"),"Ritorna l'oggetto se esiste nella borsa.");
	}
	
	@Test
	public void Test_getAttrezzo_Inesistente() {
		aggiungiOggettiBorsa();
		assertNull(this.borsa.getAttrezzo("Ramo"),"Ritorna l'oggetto ma se non esiste deve restituire NULL.");
	}
	
	
	@Test
	public void Test_GetPeso() {
		aggiungiOggettiBorsa();
		assertEquals(5,this.borsa.getPeso(),"Deve restituire la somma dei pesi presenti nella borsa(3+2=5).");
	}
	
	@Test
	public void Test_removeAttrezzo() {
		aggiungiOggettiBorsa();
		assertEquals(this.osso,this.borsa.removeAttrezzo("Osso"),"Dopo averlo rimosso ritorna l'oggetto cancellato che dovrà essere identico all'oggetto precedentemente passato al metodo add.");
	}
	
	
	
}
