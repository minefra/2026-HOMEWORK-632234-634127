import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class SimulazionePartitaTest {

	@Test
	public void Test_Fine() {
		String[] messaggi={"est","ovest","fine"};
		IOSimulator io=new IOSimulator(messaggi);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        boolean messaggioDiUscitaTrovato = false;
        for(int i = 0; i < io.getNumeroMessaggiProdotti(); i++) {
            String messaggioCorrente = io.getMessaggio(i);
            if(messaggioCorrente != null && messaggioCorrente.contains("Grazie di aver giocato!")) {
                messaggioDiUscitaTrovato = true;
                break;
            }
        }
        
        assertTrue( messaggioDiUscitaTrovato,"Controllo se la partita è terminata con il giusto messaggio");
    }
	@Test
	public void Test_Fine_CFU() {
		String[] messaggi={"vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est","vai est"};
		IOSimulator io=new IOSimulator(messaggi);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        boolean messaggioDiUscitaTrovato = false;
        for(int i = 0; i < io.getNumeroMessaggiProdotti(); i++) {
            String messaggioCorrente = io.getMessaggio(i);
            if(messaggioCorrente != null && messaggioCorrente.contains("Hai esaurito i CFU...")) {
                messaggioDiUscitaTrovato = true;
                break;
            }
        }
        
        assertTrue( messaggioDiUscitaTrovato,"Controllo se la partita è terminata con il giusto messaggio");
    }
	@Test
	public void Test_Vittoria_() {
		String[] messaggi={"vai sud","prendi lanterna","vai ovest","posa lanterna","prendi chiave","vai est","posa chiave","vai nord"};
		IOSimulator io=new IOSimulator(messaggi);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        boolean messaggioDiUscitaTrovato = false;
        for(int i = 0; i < io.getNumeroMessaggiProdotti(); i++) {
            String messaggioCorrente = io.getMessaggio(i);
            if(messaggioCorrente != null && messaggioCorrente.contains("Hai vinto!!")) {
                messaggioDiUscitaTrovato = true;
                break;
            }
        }
        
        assertTrue( messaggioDiUscitaTrovato,"Controllo se la partita è terminata con il giusto messaggio");
    }
	
	
	

}
