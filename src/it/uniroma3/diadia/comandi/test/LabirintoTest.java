
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;
class LabirintoTest {

	Labirinto lab;
	@BeforeEach
	public void setUp() {
		Stanza stanzaCorrente=new Stanza("Atrio");
		Stanza stanzaVincente=new Stanza("Biblioteca");
		this.lab=new Labirinto(stanzaCorrente,stanzaVincente);
		this.lab.creaStanze();
	}
	@Test
	public void Test_getStanzaVincente_Biblioteca() {
		assertEquals("Biblioteca",this.lab.getStanzaVincente().getNome(),"La stanza vincente deve essere uguale a quella passata al metodo costruttore.");
	}
	
	@Test
	public void Test_setStanzaVincente_Atrio() {
		this.lab.setStanzaVincente(this.lab.getStanzaCorrente());
		assertEquals("Atrio",this.lab.getStanzaVincente().getNome(),"Verifica che il set abbia cambiato l'istanza");
	}
	@Test
	public void Test_creaStanze_Controllo_Attrezzo_Vero() {
		assertTrue(this.lab.getStanzaCorrente().hasAttrezzo("osso"),"nella stanza corrente che corrisponde all'atrio deve essere presente l'oggetto osso");
	}
}
