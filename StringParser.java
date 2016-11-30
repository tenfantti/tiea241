package ohjelma;

/**
 * @author Janita Sallanko ja Anette Karhu
 * @version 16.11.2016
 * Käyttäjän syötteen parsimiseen tehty luokka.
 */
public class StringParser {
	
	private static boolean onkoOikein = false;
	private static String[] syotePaloina = new String[]{};
	private static int kohtaTaulukossa = -1;


	/**
	 * Testipääohjelma.
	 * @param args ei käytössä
	 */
	public static void main(String[] args)  {
		String[] sanoja = new String[]{"ota", "kahvi"};
		boolean onko = onkoCFG(sanoja);
		if (onko) System.out.println("Toimii!");
	}
	
	/**
	 * Tarkistaa annetun syötteen ja kutsuu metodia joka tarkistaa kieliopin syötteestä.
	 * @param syote käyttäjän antama syöte
	 */
	public static void parsi(String syote) {
		String[] vastaus = syote.split("[ ]+");
		int i = 0;
		while (i < vastaus.length) {
			vastaus[i] = vastaus[i].replaceAll("[^A-Za-zÄäÖöÅå]", "");
			vastaus[i] = vastaus[i].toLowerCase();
			i++;
		}
		boolean onko = onkoCFG(vastaus);
		syotePaloina = vastaus;
		if (onko) onkoOikein = true;
		else if (!(onko)) onkoOikein = false;
	}
	
	/**
	 * @return onko syöte kontekstittoman kielopin mukainen.
	 */
	public static boolean annaRatkaisu() {
		return onkoOikein;
	}
	
	/**
	 * @return palauttaa syötteen paloina.
	 */
	public static String[] annaPalaset() {
		return syotePaloina;
	}
	
	/**
	 * Käy läpi annettua syötettä ja tarkistaa mitä kieliopin mukaista syötettä se vastaa.
	 * Hyväksyy jos on kieliopin ymmärrettävässä muodossa ja muuten hylkää syötteen.
	 * @param sanat käyttäjän syöte
	 * @return onko kieliopin mukainen vaiko ei.
	 */
	public static boolean onkoCFG(String[] sanat) {
		String syote = "";
		
		for (String s : sanat) {
		    if ("katso".contains(s) || "paina".contains(s) || "jatka".contains(s) || "nouse".contains(s) || "jää".contains(s) || "mene".contains(s) || "keitä".contains(s) || 
		    		"juo".contains(s) ||"ota".contains(s) || "unohda".contains(s) || "juokse".contains(s) || "ole".contains(s)) {
						syote += "verbi";
					}
			else if ("aikaa".contains(s) || "tenttiin".contains(s) || "keittiö".contains(s) || s.contains("kahvi") || s.contains("kello") || s.contains("torkku") || "autolla".contains(s) || "pyörällä".contains(s)  || "nukkumaan".contains(s) || "ylös".contains(s)) {
						syote += "kohde";
			}
			else if ("ja".contains(s)) {
						syote += "rinnastuskonjuktio";
			}
			else if ("ei".contains(s)) {
						syote += "negaatio";
			}
			else System.out.println("Ei ole validi syöte");
		}
		return syote.matches("((.*)verbi(.*)kohde(.*))");
	}
	
	/**
	 * Etsii indeksin taulukossa sijaitsevalle verbille.
	 * @param sanat käyttäjän antama syöte
	 * @return palauttaa verbin kohdan taulukossa.
	 */
	public static int verbinPaikkaTaulukossa(String[] sanat) {
		if (sanat.length > 0) {
			for (int i = 0; i < sanat.length; i++) {
				  if ( "paina".contains(sanat[i]) || "nouse".contains(sanat[i]) || "mene".contains(sanat[i]) || "keitä".contains(sanat[i]) || 
						  "juo".contains(sanat[i]) || "ota".contains(sanat[i]) || "juokse".contains(sanat[i]) || "jää".contains(sanat[i])) {
					   kohtaTaulukossa =  i;
				  }
			}	
		}
		return kohtaTaulukossa;
	}
	
}
