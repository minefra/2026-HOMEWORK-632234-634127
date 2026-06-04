package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

    private List<String> righeDaLeggere;
    private int indiceRigheDaLeggere;



    private List<String> messaggiProdotti;
    private int indiceMessaggiProdotti;
    public IOSimulator(List<String> comandi) {
    	this.righeDaLeggere=comandi;
    	this.indiceRigheDaLeggere=0;
    	this.messaggiProdotti = new ArrayList<>();
    	this.indiceMessaggiProdotti=0;
    }
	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti.add(msg);
	}

	@Override
	public String leggiRiga() {	
		String riga=this.righeDaLeggere.get(this.indiceRigheDaLeggere);
		this.indiceRigheDaLeggere++;
		return riga;
		
	}
	
	public List<String> getMessaggiProdotti() {
        return this.messaggiProdotti;
    }

    public int getNumeroMessaggiProdotti() {
        return this.indiceMessaggiProdotti;
    }

    public String getMessaggio(int indice) {
        if (indice >= 0 && indice < this.indiceMessaggiProdotti) {
            return this.messaggiProdotti.get(indice);
        }
        return null;
    }
	

}
