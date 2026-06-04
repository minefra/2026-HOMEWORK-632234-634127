package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String MESSAGGIO_DONO = Costanti.getMessaggioDonoMago();
	private static final String MESSAGGIO_SCUSE = Costanti.getMessaggioScusaMago();
	private Attrezzo attrezzo;

			
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
			
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getLab().getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo attrezzoConPesoDimezato=new Attrezzo(attrezzo.getNome(),attrezzo.getPeso()/2);
		partita.getLab().getStanzaCorrente().addAttrezzo(attrezzoConPesoDimezato);
		String msg="Grazie per il regalo, per ringraziarti ho posato nella stanza l'oggetto "+attrezzoConPesoDimezato.toString()+" rendendolo piu leggero con una magia";
		return msg;
	}


}
