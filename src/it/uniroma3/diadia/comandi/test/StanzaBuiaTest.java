import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	@BeforeEach
	public void setUp() {
		this.stanzaBuia=new StanzaBuia("Sala");
	}

	@Test
	public void Test_GetAttrezzoSpeciale_Default_Vero() {
		assertEquals("lanterna",this.stanzaBuia.getAttrezzoSpeciale(),"Se l'attrezzo speciale non viene passato al metodo costruttore, allora verrà istanziato quello di default cioè la lanterna.");
	}
	@Test
	public void Test_SetAttrezzoSpeciale_Vero() {
		this.stanzaBuia.setAttrezzoSpeciale("torcia");
		assertEquals("torcia",this.stanzaBuia.getAttrezzoSpeciale(),"Controllo del set se ha passato la giusta stringa");
	}
	@Test
	public void Test_Descrizione_Senza_Attrezzo_Speciale_Vero() {
		String messaggio="Qui c'è buio pesto";
		assertTrue(messaggio.equals(this.stanzaBuia.getDescrizione()),"Se l'attrezzo speciale non è presente nella stanza, allora il metodo get descrizione deve ritornare 'Qui c'è buio pesto' e non è possibile vedere i dettagli della stanza.");
	}
	@Test
	public void Test_Descrizione_Con_Attrezzo_Speciale_Falso() {
		String messaggio="Qui c'è buio pesto";
		Attrezzo Lanterna=new Attrezzo("lanterna",2);
		this.stanzaBuia.addAttrezzo(Lanterna);
		assertFalse(messaggio.equals(this.stanzaBuia.getDescrizione()),"Se l'attrezzo speciale è presente nella stanza, allora sarà possibile vedere i dettagli della stanza.");
	}

}
