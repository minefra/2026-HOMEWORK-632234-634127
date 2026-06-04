package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO {
	Scanner scanner;
	public IOConsole(Scanner scanner) {
		this.scanner=scanner;
	}
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		// Legge l'input senza chiudere o ricreare lo Scanner
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
	}
}