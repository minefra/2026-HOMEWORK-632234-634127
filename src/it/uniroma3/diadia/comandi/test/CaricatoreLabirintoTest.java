import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Stanza;
import java.io.StringReader;

	class CaricatoreLabirintoTest {
		    @Test
		    public void testCaricatoreLabirintoMonolocale() throws FormatoFileNonValidoException {
		        String specifica = 
		            "Stanze: N10\n" +
		            "Stanze speciali:\n"+
		            "Inizio: N10\n" +
		            "Vincente: N10\n" +
		            "Attrezzi:\n" +    
		            "Uscite:\n" +      
		            "Personaggi:\n";   

		        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		        caricatore.carica();
		        
		        Stanza iniziale = caricatore.getStanzaIniziale();
		        Stanza vincente = caricatore.getStanzaVincente();
		        
		        // Verifiche
		        assertNotNull(iniziale);
		        assertNotNull(vincente);
		        assertEquals("N10", iniziale.getNome());
		        assertEquals("N10", vincente.getNome());
		        assertEquals(iniziale, vincente); 
		    }

		    @Test
		    public void testCaricatoreLabirintoBilocale() throws FormatoFileNonValidoException {
		        
		        String specifica = 
		            "Stanze: N10, Biblioteca\n" +
		            "Stanze speciali:\n"+
		            "Inizio: N10\n" +
		            "Vincente: Biblioteca\n" +
		            "Attrezzi: osso 2 N10\n" +
		            "Uscite: N10 nord Biblioteca, Biblioteca sud N10\n" +
		            "Personaggi:\n";

		        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		        caricatore.carica();
		        
		        Stanza iniziale = caricatore.getStanzaIniziale();
		        Stanza vincente = caricatore.getStanzaVincente();
		        
		       
		        assertEquals("N10", iniziale.getNome());
		        assertEquals("Biblioteca", vincente.getNome());
		        
		       
		        assertTrue(iniziale.hasAttrezzo("osso"));
		        assertFalse(vincente.hasAttrezzo("osso"));
		        
		      
		        assertEquals(vincente, iniziale.getStanzaAdiacente(Direzione.NORD));
		        assertEquals(iniziale, vincente.getStanzaAdiacente(Direzione.SUD));
		    }
		    
		    @Test
		    public void testCaricatoreLabirintoTrilocaleConTutto() throws FormatoFileNonValidoException {
		        String specifica = 
		            "Stanze: Atrio, N10, N11\n" +
		            "Stanze speciali:\n"+
		            "Inizio: Atrio\n" +
		            "Vincente: N11\n" +
		            "Attrezzi: chiave 1 Atrio, lanterna 3 N10, spada 5 N11\n" +
		            "Uscite: Atrio nord N10, Atrio est N11, N10 sud Atrio, N11 ovest Atrio\n" +
		            "Personaggi:\n";

		        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		        caricatore.carica();
		        
		        Stanza atrio = caricatore.getStanzaIniziale();
		        Stanza n11 = caricatore.getStanzaVincente();
		        
		    
		        Stanza n10 = atrio.getStanzaAdiacente(Direzione.NORD); 
		        
		     
		        assertNotNull(n10);
		        assertEquals("N10", n10.getNome());
		        
		       
		        assertTrue(atrio.hasAttrezzo("chiave"));
		        assertTrue(n10.hasAttrezzo("lanterna"));
		        assertTrue(n11.hasAttrezzo("spada"));
		        

		        assertEquals(n11, atrio.getStanzaAdiacente(Direzione.EST));
		        assertEquals(atrio, n11.getStanzaAdiacente(Direzione.OVEST));
		    }

		    @Test
		    public void testFormatoFileNonValidoException() {
		        String specificaErrata = 
		            "Stanze: N10, Biblioteca\n" +
		            "Stanze speciali:\n"+
		            "Vincente: Biblioteca\n" +
		            "Attrezzi:\n" +
		            "Uscite:\n" +
		            "Personaggi:\n";

		        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specificaErrata));
		        
		 
		        assertThrows(FormatoFileNonValidoException.class, () -> {
		            caricatore.carica(); 
		        });
		    }
		    @Test
		    public void testCaricatoreLabirintoConPersonaggi() throws FormatoFileNonValidoException {
		        String specifica = 
		            "Stanze: Atrio, N10, N11\n" +
		            "Stanze speciali:\n"+
		            "Inizio: Atrio\n" +
		            "Vincente: N11\n" +
		            "Attrezzi:\n" +
		            "Uscite: Atrio nord N10, Atrio est N11\n" +
		            "Personaggi: Cane Fido Bau! Atrio, Mago Merlino Sono_Merlino N10 bacchetta 5, Strega Varana Ahah! N11\n";

		        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		        caricatore.carica();
		        
		        Stanza atrio = caricatore.getStanzaIniziale();
		        Stanza n11 = caricatore.getStanzaVincente();
		        Stanza n10 = atrio.getStanzaAdiacente(Direzione.NORD); 
		        
		       
		        assertNotNull(atrio.getPersonaggio(), "L'Atrio dovrebbe contenere un personaggio");
		        assertEquals("Fido", atrio.getPersonaggio().getNome());
		        
		   
		        assertNotNull(n10.getPersonaggio(), "La stanza N10 dovrebbe contenere un personaggio");
		        assertEquals("Merlino", n10.getPersonaggio().getNome());
		        
		      
		        assertNotNull(n11.getPersonaggio(), "La stanza N11 (vincente) dovrebbe contenere un personaggio");
		        assertEquals("Varana", n11.getPersonaggio().getNome());
		    }
		}
