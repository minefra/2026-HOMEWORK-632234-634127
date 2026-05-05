import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabricaDiComandiFisarmonica;

class FabricaDiComandiFisarmonicaTest {
	public FabricaDiComandiFisarmonica fis;
	private Comando com;
	@BeforeEach
	public void setUp() {
		fis=new FabricaDiComandiFisarmonica(); 
	}
	@Test
	public void Test_ComandoNonValido_Vero() {
		com=fis.costruisciComando("Ciao");
		assertEquals("Comando non valido",com.getNome(),"Test riconoscimento del comando non valido.");
	}
	@Test
	public void Test_ComandoVai_Vero() {
		com=fis.costruisciComando("vai");
		assertEquals("Vai",com.getNome(),"Test riconoscimento del comando 'vai'.");
	}
	@Test
	public void Test_ComandoPrendi_Vero() {
		com=fis.costruisciComando("prendi");
		assertEquals("Prendi",com.getNome(),"Test riconoscimento del comando 'prendi'.");
	}
	@Test
	public void Test_ComandoPosa_Vero() {
		com=fis.costruisciComando("posa");
		assertEquals("Posa",com.getNome(),"Test riconoscimento del comando 'posa'.");
	}
	@Test
	public void Test_ComandoAiuto_Vero() {
		com=fis.costruisciComando("aiuto");
		assertEquals("Aiuto",com.getNome(),"Test riconoscimento del comando 'aiuto'.");
	}
	@Test
	public void Test_ComandoFine_Vero() {
		com=fis.costruisciComando("fine");
		assertEquals("Fine",com.getNome(),"Test riconoscimento del comando 'fine'.");
	}
	@Test
	public void Test_ComandoGuarda_Vero() {
		com=fis.costruisciComando("guarda");
		assertEquals("Guarda",com.getNome(),"Test riconoscimento del comando 'guarda'.");
	}

}
