import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.jupiter.api.BeforeEach;
class GiocatoreTest {
	private Giocatore giocatore;
	@BeforeEach
	public void setUp() {
		this.giocatore=new Giocatore();	
	}
	@Test
	public void Test_GetCfu_InzioPartita() {
		assertEquals(20,this.giocatore.getCfu(),"I cfu se chiamato il metodo crostruttore, deve inizializzare i CFU iniziali con il valore (20).");
	}
	
	@Test
	public void Test_SetCfu_0() {
		this.giocatore.setCfu(0);
		assertEquals(0,this.giocatore.getCfu(),"Deve essere possibile impostare i cfu a 0.");
	}
	
	@Test
	public void Test_getBorsa_notNull() {
		assertNotNull(this.giocatore.getBorsa(),"Questo riferimento NON deve essere null.");
	}

}
