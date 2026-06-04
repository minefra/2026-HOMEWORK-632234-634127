import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagica_Test {
	private StanzaMagica s;
	@BeforeEach
	public void setUp() {
		this.s=new StanzaMagica("Salotto",2);
	}
	
	@Test
	public void Test_addAttrezzo_Soglia_Non_Superata_Vero() {
		Attrezzo martello=new Attrezzo("martello",2);
		this.s.addAttrezzo(martello);
		assertTrue(this.s.getAttrezzi().contains(martello));
	}
	@Test
	public void Test_addAttrezzo_Soglia_Superata_Falso() {
		Attrezzo martello=new Attrezzo("martello",2);
		Attrezzo spada=new Attrezzo("spada",4);
		Attrezzo ascia=new Attrezzo("ascia",7);
		this.s.addAttrezzo(ascia);
		this.s.addAttrezzo(spada);
		this.s.addAttrezzo(martello);
		assertFalse(this.s.getAttrezzi().contains(martello));
	}
	@Test
	public void Test_addAttrezzo_Soglia_Superata_e_Oggetto_Modificato_Vero() {
		Attrezzo martello=new Attrezzo("martello",2);
		Attrezzo spadaMod=new Attrezzo("adaps",8);
		Attrezzo spada=new Attrezzo("spada",4);
		Attrezzo ascia=new Attrezzo("ascia",7);
		this.s.addAttrezzo(ascia);
		this.s.addAttrezzo(martello);
		this.s.addAttrezzo(spada);
		assertTrue(this.s.getAttrezzi().contains(spadaMod));
		
	}
}
