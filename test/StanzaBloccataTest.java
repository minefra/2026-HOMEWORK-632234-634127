import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	@BeforeEach
	public void setUp(){
		this.stanzaBloccata=new StanzaBloccata("sala","nord");
	}

	@Test
	public void Test_GetAttrezzo_Speciale_Default_Vero() {
		assertEquals("chiave",this.stanzaBloccata.getAttrezzoSpeciale(),"Se l'attrezzo speciale non è passato dal metodo costruttore, allora viene istanziato quello di default cioè la chiave.");
	}
	
	@Test
	public void Test_SetAttrezzo_Speciale_Vero() {
		this.stanzaBloccata.setAttrezzoSpeciale("maniglia");
		assertEquals("maniglia",this.stanzaBloccata.getAttrezzoSpeciale(),"Dopo il set il get deve restituire ciò che ha passato il set.");
	}
	@Test
	public void Test_GetDirezioneBloccata_Vero() {
		assertEquals("nord",this.stanzaBloccata.getDirezioneBloccata(),"Deve restituire la direzione bloccata,cioè la stessa passata al metodo costruttore.");
	}
	@Test
	public void Test_SetDirezioneBloccata_Vero() {
		this.stanzaBloccata.setDirezioneBloccata("sud");
		assertEquals("sud",this.stanzaBloccata.getDirezioneBloccata(),"Dopo il set la direzione bloccata cambia.");
	}
	@Test
	public void Test_getStanzaAdiacente_Senza_AttrezzoSpeciale_Vero() {
		assertTrue(this.stanzaBloccata.equals(stanzaBloccata.getStanzaAdiacente("nord")),"Senza l'attrezzo speciale se proviamo ad andare nella direzione bloccata rimaniamo nella stanza attuale.");
	}
	@Test
	public void Test_getStanzaAdiacente_Con_AttrezzoSpeciale_Falso() {
		Attrezzo chiave=new Attrezzo("chiave",2);
		this.stanzaBloccata.addAttrezzo(chiave);
		assertFalse(this.stanzaBloccata.equals(stanzaBloccata.getStanzaAdiacente("nord")),"Con l'attrezzo speciale se proviamo ad andare nella stanza bloccata, quest'ultima sarà accessibile.");
	}


}
