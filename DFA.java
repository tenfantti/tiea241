package ohjelma;

/**
 * @author Anette Karhu ja Janita Sallanko
 * @version 20.11.2016
 * DFA luokka joka sis‰lt‰‰ tilasiirtym‰t.
 */
public class DFA {
	
	@SuppressWarnings("unused")
	private int[] tilat = new int[]{};
	private static int aloitustila = 0;
	private static int tila = 0;
	private static boolean hyvaksyy = false;
	private static boolean onkoHylkaavassa = false;
	private static int kohtaTarinassa = -1;
	
	/**
	 * Testi p‰‰ohjelma.
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		String[] sanoja = new String[]{"j‰‰", "nukkumaan"};
		DFA.ajaDFA(sanoja);
		if (getNykyinenTila() > 0 && getOnkoHylkaavaTila() == false) System.out.println("Toimii!");
	}
	
	/**
	 * @return palauttaa aloitustilan
	 */
	public static int getAloitus() {
		return aloitustila;
	}
	
	/**
	 * @return onko hyv‰ksyv‰ss‰ tilassa vai ei.
	 */
	public static boolean onkoHyvaksyva() {
		return hyvaksyy;
	}
	
	/**
	 * @return miss‰ tilavaiheessa ollaan.
	 */
	public static int getNykyinenTila() {
		return tila;
	}
	
	/**
	 * @return palauttaa onko hylk‰‰v‰ss‰ tilassa vai ei.
	 */
	public static boolean getOnkoHylkaavaTila()  {
		return onkoHylkaavassa;
	}
	
	/**
	 * @return palauttaa miss‰ vaiheessa tarinaa ollaan.
	 */
	public static int getKohtaTarinassa() {
		return kohtaTarinassa;
	}
	
	/**
	 * Sis‰lt‰‰ tilan vaiheet.
	 * @param sanat syˆte mik‰ k‰yd‰‰n l‰pi.
	 */
	public static void ajaDFA(String[] sanat) {
		String syote = sanat[StringParser.verbinPaikkaTaulukossa(sanat)];
		switch(tila)
		{
		case(0): {
			if ("j‰‰".contains(syote)) {
				tila = 1; 
				kohtaTarinassa = 2;
			}
			if ("paina".contains(syote)) {
				tila = 2;
				kohtaTarinassa = 3;
				break;
			}
			if ("nouse".contains(syote)) {
				tila = 3;
				kohtaTarinassa = 4;
				break;
			}
		}
		case(1): {
			hylkaavaTila();
			break;
		}
		case(2): {
			if ("j‰‰".contains(syote))  {
				tila = 1;
				kohtaTarinassa = 2;
			}
			if ("nouse".contains(syote)) {
				tila = 3;
				kohtaTarinassa = 4;
				break;
			}
			if ("paina".contains(syote)) {
				tila = 10;
				kohtaTarinassa = 11;
			}
		}
		case(3): {
			if ("keit‰".contains(syote)) {
				tila = 4;
				kohtaTarinassa = 5;
				break;
			}
			if ("juokse".contains(syote)) {
				tila = 5;
				kohtaTarinassa = 6;
			}
		}
		case(4): {
			if ("juo".contains(syote)) {
				tila = 6;
				kohtaTarinassa = 7;
			}
			if ("ota".contains(syote)) {
				tila = 7;
				kohtaTarinassa = 8;
				break;
			}
			if ("juokse".contains(syote)) {
				tila = 5;
				kohtaTarinassa = 6;
			}	
		}
		case(5): {
			hyvaksyvaTila();
			break;
		}
		case(6): {
			hylkaavaTila();
			break;
		}
		case(7): {
			if("juokse".contains(syote)) {
				tila = 5;
				kohtaTarinassa = 6;
			}
			if ("aja".contains(syote)) {
				tila = 8;
				kohtaTarinassa = 9;
			}
			if ("mene".contains(syote)) {
				tila = 9;
				kohtaTarinassa = 10;
			}
		}
		case(8): {
			hylkaavaTila();
			break;
		}
		case(9): {
			hylkaavaTila();
			break;
		}
		case(10): {
			hylkaavaTila();
			break;
		}
		
		default : {
			break;
		}
		}
	}

	/**
	 * Ilmoittaa ett‰ on hyv‰ksyv‰ss‰ tilassa.
	 */
	public static void hyvaksyvaTila() {
		hyvaksyy = true;
	}

	/**
	 * Ilmoittaa ett‰ on hylk‰‰v‰ss‰ tilassa.
	 */
	public static void hylkaavaTila() {
		onkoHylkaavassa = true;
		hyvaksyy = false;
	}

}
