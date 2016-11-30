package ohjelma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Janita Sallanko ja Anette Karhu
 * @version 16.11.2016
 * Pääluokka koko ohjelmalle, joka hyödyntää
 * projektin muita luokkia ohjelmassa
 */
public class Paaluokka {
	
	private static boolean onkoLoppu = false;

	/**
	 * Pääohjelma, kutsuu parseteksti aliohjelmaa ja kyselee käyttäjältä syötteitä.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		String tiedosto = "tarina.txt";
		try {
			String[] teksti = parsiTeksti(tiedosto);
			System.out.println(teksti[1]);
			while (!(onkoLoppu)) {
				kyseleKayttajalta(teksti);	
			}
			System.out.println("PELI ON PÄÄTTYNYT!");
		} catch (IOException e) {
			System.err.print("Tarinaa ei löydy, hupsis! " + e.getMessage());
		}
	}
	
	/**
	 * kyselee käyttäjältä syötettä ja tarkistaa onko syöte hyväksyttävässä muodossa, 
	 * jos ei ole niin kysytään uudestaan syötettä käyttäjältä. Muuten jatkaa 
	 * tarinaa eteenpäin. 
	 * @param teksti tarina paloiteltuna 
	 */
	@SuppressWarnings({ "unused", "resource" })
	private static void kyseleKayttajalta(String[] teksti) {
		Scanner scanner = new Scanner(System.in);
		String[] tarina = teksti;
		boolean jatkuuko = true;
		int kohta = 1; 
		String[] syotePalaset;
		String syote = "";
		while (jatkuuko) {
			System.out.println("Anna syöte: ");
			syote = scanner.nextLine();
			StringParser.parsi(syote);
			boolean onkoValidi = StringParser.annaRatkaisu();
			if (onkoValidi) {
				syotePalaset = StringParser.annaPalaset();
				DFA.ajaDFA(syotePalaset);
				if (DFA.getNykyinenTila() > 0 && DFA.onkoHyvaksyva() == false && DFA.getOnkoHylkaavaTila() == false) {
					System.out.println(teksti[DFA.getKohtaTarinassa()]);
				}
				else if (DFA.getNykyinenTila() > 0 && DFA.onkoHyvaksyva() == false && DFA.getOnkoHylkaavaTila() == true) {
					System.out.println(teksti[DFA.getKohtaTarinassa()]);
					jatkuuko = false;
					onkoLoppu = true;
					break;
				}
				else if (DFA.onkoHyvaksyva() == true) {
					System.out.println(teksti[DFA.getKohtaTarinassa()]);
					jatkuuko = false;
					onkoLoppu = true;
					break;
				}
			}
			else if (!(onkoValidi)) {
				System.out.println("En ymmärrä suomeasi, kirjoitahan uudelleen.");
				if (DFA.getKohtaTarinassa() < 0) System.out.println(teksti[1]);
				else System.out.println(teksti[DFA.getKohtaTarinassa()]);
			}
		}
		
	}

	/**
	 * Käsittelee annetun tiedoston ja parsii sen paloiksi taulukkoon.
	 * @param tiedosto tarinan tiedosto
	 * @return palauttaa taulukon paloitettuna
	 * @throws IOException jos tarinaa ei löydy
	 */
	private static String[] parsiTeksti(String tiedosto) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(tiedosto));
		try {
		    StringBuilder sb = new StringBuilder();
		    String rivi = br.readLine();

		    while (rivi != null) {
		        sb.append(rivi);
		        sb.append(System.lineSeparator());
		        rivi = br.readLine();
		    }
		    String kaikki = sb.toString();
		    String[] palaset = new String[]{};
		    if (kaikki.contains(":")) palaset = kaikki.split("\\:");
		    return palaset;
		} finally {
		    br.close();
		}
	}

}
