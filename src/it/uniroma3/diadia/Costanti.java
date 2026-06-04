package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Costanti {
	private static final String NOME_FILE = "diadia.properties";
    private static Properties properties;
    static {
        properties = new Properties();
        
        try (InputStream input = Costanti.class.getClassLoader().getResourceAsStream(NOME_FILE)) {
            if (input == null) {
                System.err.println("Attenzione: impossibile trovare il file " + NOME_FILE + " nel classpath.");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            System.err.println("Errore durante la lettura del file di configurazione: " + ex.getMessage());
        }
    }
    public static int getCFUIniziali() {
        String cfu = properties.getProperty("cfu.iniziali", "20");
        return Integer.parseInt(cfu);
    }

    public static int getPesoMaxBorsa() {
        String peso = properties.getProperty("borsa.peso.max", "10");
        return Integer.parseInt(peso);
    }
    public static String getAttrezzoSpecialeStanzaBloccata() {
        return properties.getProperty("attrezzo.speciale.stanza.bloccata", "chiave");
    }
    public static String getAttrezzoSpecialeStanzaBuia() {
        return properties.getProperty("attrezzo.speciale.stanza.buia", "lanterna");
    }
    public static int getSogliaMagicaStanzaMagica() {
        String soglia = properties.getProperty("soglia.magica.stanza.magica", "3");
        return Integer.parseInt(soglia);
    }
    public static String getMessaggioDonoMago() {
        return properties.getProperty("messaggio.dono.mago", "Sei un vero simpaticone,con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!");
    }
    public static String getMessaggioScusaMago() {
        return properties.getProperty("messaggio.scuse.mago", "Mi spiace, ma non ho piu' nulla...");
    }
    public static String getMessaggioPermalosoStrega() {
        return properties.getProperty("messaggio.permaloso.strega", "Non mi hai salutato!!... sei stato spostato in una stanza con meno attrezzi...");
    }
    public static String getMessaggioBuonoStrega() {
        return properties.getProperty("messaggio.buono.strega", "Sei stato spostato in una stanza con piu attrezzi.");
    }
    public static String getMessaggioInterazioneCane() {
        return properties.getProperty("messaggio.interazione.cane", "Woof Woof!! ops il cane di ha morso -1 CFU");
    }
    public static String getCiboPreferitoCane() {
        return properties.getProperty("cibo.preferito.cane", "croccantini");
    }
}
