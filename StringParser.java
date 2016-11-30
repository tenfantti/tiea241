package ohjelma;

/**
 * @author Janita Sallanko ja Anette Karhu
 * @version 16.11.2016
 * K�ytt�j�n sy�tteen parsimiseen tehty luokka.
 */
public class StringParser {
	
	private static boolean onkoOikein = false;
	private static String[] syotePaloina = new String[]{};
	private static int kohtaTaulukossa = -1;


	/**
	 * Testip��ohjelma.
	 * @param args ei k�yt�ss�
	 */
	public static void main(String[] args)  {
		String[] sanoja = new String[]{"ota", "kahvi"};
		boolean onko = onkoCFG(sanoja);
		if (onko) System.out.println("Toimii!");
	}
	
	/**
	 * Tarkistaa annetun sy�tteen ja kutsuu metodia joka tarkistaa kieliopin sy�tteest�.
	 * @param syote k�ytt�j�n antama sy�te
	 */
	public static void parsi(String syote) {
		String[] vastaus = syote.split("[ ]+");
		int i = 0;
		while (i < vastaus.length) {
			vastaus[i] = vastaus[i].replaceAll("[^A-Za-z������]", "");
			vastaus[i] = vastaus[i].toLowerCase();
			i++;
		}
		boolean onko = onkoCFG(vastaus);
		syotePaloina = vastaus;
		if (onko) onkoOikein = true;
		else if (!(onko)) onkoOikein = false;
	}
	
	/**
	 * @return onko sy�te kontekstittoman kielopin mukainen.
	 */
	public static boolean annaRatkaisu() {
		return onkoOikein;
	}
	
	/**
	 * @return palauttaa sy�tteen paloina.
	 */
	public static String[] annaPalaset() {
		return syotePaloina;
	}
	
	/**
	 * K�y l�pi annettua sy�tett� ja tarkistaa mit� kieliopin mukaista sy�tett� se vastaa.
	 * Hyv�ksyy jos on kieliopin ymm�rrett�v�ss� muodossa ja muuten hylk�� sy�tteen.
	 * @param sanat k�ytt�j�n sy�te
	 * @return onko kieliopin mukainen vaiko ei.
	 */
	public static boolean onkoCFG(String[] sanat) {
		String syote = "";
		
		for (String s : sanat) {
		    if ("katso".contains(s) || "paina".contains(s) || "jatka".contains(s) || "nouse".contains(s) || "j��".contains(s) || "mene".contains(s) || "keit�".contains(s) || 
		    		"juo".contains(s) ||"ota".contains(s) || "unohda".contains(s) || "juokse".contains(s) || "ole".contains(s)) {
						syote += "verbi";
					}
			else if ("aikaa".contains(s) || "tenttiin".contains(s) || "keitti�".contains(s) || s.contains("kahvi") || s.contains("kello") || s.contains("torkku") || "autolla".contains(s) || "py�r�ll�".contains(s)  || "nukkumaan".contains(s) || "yl�s".contains(s)) {
						syote += "kohde";
			}
			else if ("ja".contains(s)) {
						syote += "rinnastuskonjuktio";
			}
			else if ("ei".contains(s)) {
						syote += "negaatio";
			}
			else System.out.println("Ei ole validi sy�te");
		}
		return syote.matches("((.*)verbi(.*)kohde(.*))");
	}
	
	/**
	 * Etsii indeksin taulukossa sijaitsevalle verbille.
	 * @param sanat k�ytt�j�n antama sy�te
	 * @return palauttaa verbin kohdan taulukossa.
	 */
	public static int verbinPaikkaTaulukossa(String[] sanat) {
		if (sanat.length > 0) {
			for (int i = 0; i < sanat.length; i++) {
				  if ( "paina".contains(sanat[i]) || "nouse".contains(sanat[i]) || "mene".contains(sanat[i]) || "keit�".contains(sanat[i]) || 
						  "juo".contains(sanat[i]) || "ota".contains(sanat[i]) || "juokse".contains(sanat[i]) || "j��".contains(sanat[i])) {
					   kohtaTaulukossa =  i;
				  }
			}	
		}
		return kohtaTaulukossa;
	}
	
}
