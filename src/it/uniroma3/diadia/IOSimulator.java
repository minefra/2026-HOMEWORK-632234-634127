package it.uniroma3.diadia;

public class IOSimulator implements IO {

    private String[] righeDaLeggere;
    private int indiceRigheDaLeggere;


    private String[] messaggiProdotti;
    private int indiceMessaggiProdotti;
    public IOSimulator(String []comandi) {
    	this.righeDaLeggere=comandi;
    	this.indiceRigheDaLeggere=0;
    	
    	this.messaggiProdotti = new String[4096];
    	this.indiceMessaggiProdotti=0;
    }
	@Override
	public void mostraMessaggio(String msg) {
		if(this.indiceMessaggiProdotti<this.messaggiProdotti.length) {
			this.messaggiProdotti[this.indiceMessaggiProdotti]=msg;
			this.indiceMessaggiProdotti++;
		}
	}

	@Override
	public String leggiRiga() {	
		if(this.indiceRigheDaLeggere<this.righeDaLeggere.length) {
			String riga=this.righeDaLeggere[this.indiceRigheDaLeggere];
			this.indiceRigheDaLeggere++;
			return riga;
		}
		return null;
	}
	public String[] getMessaggiProdotti() {
        return this.messaggiProdotti;
    }

    public int getNumeroMessaggiProdotti() {
        return this.indiceMessaggiProdotti;
    }

    public String getMessaggio(int indice) {
        if (indice >= 0 && indice < this.indiceMessaggiProdotti) {
            return this.messaggiProdotti[indice];
        }
        return null;
    }
	

}
