package ohjelma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Janita Sallanko ja Anette Karhu
 * @version 16.11.2016
 * P��luokka koko ohjelmalle, joka hy�dynt��
 * projektin muita luokkia ohjelmassa
 */
public class Paaluokka {
	
	private static boolean onkoLoppu = false;

	/**
	 * P��ohjelma, kutsuu parseteksti aliohjelmaa ja kyselee k�ytt�j�lt� sy�tteit�.
	 * @param args ei k�yt�ss�
	 */
	public static void main(String[] args) {
		String tiedosto = "tarina.txt";
		try {
			String[] teksti = parsiTeksti(tiedosto);
			System.out.println(teksti[1]);
			while (!(onkoLoppu)) {
				kyseleKayttajalta(teksti);	
			}
			System.out.println("PELI ON P��TTYNYT!");
		} catch (IOException e) {
			System.err.print("Tarinaa ei l�ydy, hupsis! " + e.getMessage());
		}
	}
	
	/**
	 * kyselee k�ytt�j�lt� sy�tett� ja tarkistaa onko sy�te hyv�ksytt�v�ss� muodossa, 
	 * jos ei ole niin kysyt��n uudestaan sy�tett� k�ytt�j�lt�. Muuten jatkaa 
	 * tarinaa eteenp�in. 
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
			System.out.println("Anna sy�te: ");
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
				System.out.println("En ymm�rr� suomeasi, kirjoitahan uudelleen.");
				if (DFA.getKohtaTarinassa() < 0) System.out.println(teksti[1]);
				else System.out.println(teksti[DFA.getKohtaTarinassa()]);
			}
		}
		
	}

	/**
	 * K�sittelee annetun tiedoston ja parsii sen paloiksi taulukkoon.
	 * @param tiedosto tarinan tiedosto
	 * @return palauttaa taulukon paloitettuna
	 * @throws IOException jos tarinaa ei l�ydy
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
