import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {
	
	private Partita partita;
	@BeforeEach
	public void setUp() {
		this.partita=new Partita();
	}
	
	@Test
	public void Test_isFinita_Inizio_Partita_Falso() {
		assertFalse(this.partita.isFinita(),"Una partita appena cominciata non puo essere finita");
	}
	
	@Test
	public void Test_isFinita_SetFinita_Vero() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita(),"Dopo aver impostato setFinita il risultato deve essere vero");
	}
	
	@Test
	public void Test_isFinita_0Cfu_Vero() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita(),"Quando il giocatore termina i CFU il gioco deve terminare");
	}
	
	
	@Test
	public void Test_vinta_Inizio_Partita_Falso() {
		assertFalse(this.partita.vinta(),"Appena cominciato il gioco la partita non puo essere subito vinta perche la stanza corrente (iniziale),non corrisponde a quella vincente.");
	}
	
	@Test
	public void Test_vinta_Imposta_Finita_Non_Stanza_Vincente_Falso() {
		this.partita.setFinita();
		assertFalse(this.partita.vinta(),"Deve essere falso perchè se non viene fatta alcuna mossa ma solamente impostando finita la partita non puo essere automaticamente vinta.");
	}
	
	public void impostaStanzaCorrente_Uguale_stanzaVincente() {
		Labirinto lab=this.partita.getLab();
		lab.setStanzaCorrente(lab.getStanzaVincente());
	}
	@Test
	public void Test_vinta_Impostando_StanzaCorrente_La_Vincente_Vero() {
		impostaStanzaCorrente_Uguale_stanzaVincente();
		assertTrue(this.partita.vinta(),"La partita deve essere vinta quando si arriva alla stanza obiettivo, quindi il metodo vinta deve restituire True.");
	}
	
	@Test
	public void Test_isFinita_Partita_Vinta_corrisponde_Partita_Finita_Vero() {
		impostaStanzaCorrente_Uguale_stanzaVincente();
		assertTrue(this.partita.isFinita(),"Se la partita è vinta deve automaticamente terminare.");
	}
}
