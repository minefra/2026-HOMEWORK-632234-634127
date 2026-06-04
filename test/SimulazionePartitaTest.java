import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class SimulazionePartitaTest {

	@Test
	public void Test_Fine() {
		String[] messaggi={"vai est","vai ovest","fine"};
		List<String> lista=new ArrayList<String>();
		lista.addAll(Arrays.asList(messaggi));
		IOSimulator io=new IOSimulator(lista);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        Iterator<String> j=io.getMessaggiProdotti().iterator();
        boolean messaggioDiUscitaTrovato=false;
        while(j.hasNext()) {
        	String messaggioCorrente = j.next();
            if(messaggioCorrente != null && messaggioCorrente.contains("Grazie di aver giocato!")) {
                messaggioDiUscitaTrovato = true;
                break;
            }
        }
        
        assertTrue( messaggioDiUscitaTrovato,"Controllo se la partita è terminata con il giusto messaggio");
    }
	@Test
	public void Test_Fine_CFU() {
		ArrayList<String> lista=new ArrayList<String>();
		for(int i=0;i<21;i++)lista.add("vai est");
		IOSimulator io=new IOSimulator(lista);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        boolean messaggioDiUscitaTrovato = false;
        Iterator<String> j=io.getMessaggiProdotti().iterator();
        while(j.hasNext()) {
            String messaggioCorrente = j.next();
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
		List<String> lista=new ArrayList<String>();
		lista.addAll(Arrays.asList(messaggi));
		IOSimulator io=new IOSimulator(lista);
		DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        boolean messaggioDiUscitaTrovato = false;
        Iterator<String> j=io.getMessaggiProdotti().iterator();
        while(j.hasNext()) {
        	  String messaggioCorrente = j.next();
            if(messaggioCorrente != null && messaggioCorrente.contains("Hai vinto!!")) {
                messaggioDiUscitaTrovato = true;
                break;
            }
        }
        
        assertTrue( messaggioDiUscitaTrovato,"Controllo se la partita è terminata con il giusto messaggio");
    }
	
	
	

}
