import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandoRiflessivaTest {
	public FabbricaDiComandiRiflessiva fis;
	
	@BeforeEach
	public void setUp() {
		this.fis=new FabbricaDiComandiRiflessiva();
	}
	@Test
	public void TestComandoNonRinosciuto_Vero() {
		fis.costruisciComando("Ciao");
		assertEquals("Comando non valido",fis.costruisciComando("Ciao").getNome());
	}
}
