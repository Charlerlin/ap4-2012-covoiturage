package membre;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**Gestion des préférences pour les membre de façon normalisée permettant la recherche
 * @author charlerlin
 *
 */
public class Preferences {
	protected HashMap<String, Boolean> preferences;
	
	/**Création d'une nouvelle table de correspondance pour les préférences vide
	 * 
	 */
	public Preferences() {
		preferences = new HashMap<String, Boolean>();
	}
	
	public static void main(String[] args) {
		Preferences pref = Preferences.creerPrefConsole();
		System.out.println(pref.toString());
		//TODO
	}
	
	/**Ajout d'une préférence par intitulé et état
	 * @param s intitulé de la préférence
	 * @param b état de la préférence
	 */
	public void put(String s, Boolean b){
		preferences.put(s, b);
	}
	
	public String toString(){
		String positif = "+ = ";
		String negatif = ", - = ";
		for(Entry<String, Boolean> entry : preferences.entrySet()){
			String cle = entry.getKey();
		    Boolean valeur = entry.getValue();
		    if(valeur){
		    	positif += cle+" ";
		    }
		    else{
		    	negatif += cle+" ";
		    }
		}
		return positif+negatif;
	}
	
	/**Création d'un ensemble de préférences correspondant aux strings entrées dans la méthode (à des fins de démonstration uniquement)
	 * @return Une table de préférences
	 */
	public static Preferences creerPrefConsole(){
		String[] basePrefs = {"Conversation", "Musique", "Animaux", "Fumeur"};
		Preferences listePrefs = new Preferences(); 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Répondez avec 'o' ou 'n'.");
		for(String s : basePrefs){
			System.out.print(s+" ? ");
			String se = sc.nextLine();
			while(!(se.equals("o")||se.equals("n"))){
				System.out.println("Répondre avec 'o' ou 'n'.");
				System.out.print(s+" ");
				se = sc.nextLine();
			}
			if(se.charAt(0)=='o'){
				listePrefs.put(s, true);
			}
			else{
				listePrefs.put(s, false);
			}
		}
		
		return listePrefs;
	}
}
