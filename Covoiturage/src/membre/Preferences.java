package membre;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Preferences {
	protected HashMap<String, Boolean> preferences;
	
	public Preferences() {
		preferences = new HashMap<String, Boolean>();
	}
	
	public static void main(String[] args) {
		Preferences pref = Preferences.creerPrefConsole();
		System.out.println(pref.toString());
		//TODO
	}
	
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
	
	public static Preferences creerPrefConsole(){
		String[] basePrefs = {"Conversation", "Musique", "Animaux", "Fumeur"};
		Preferences listePrefs = new Preferences(); 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("RŽpondez avec 'o' ou 'n'.");
		for(String s : basePrefs){
			System.out.print(s+" ? ");
			String se = sc.nextLine();
			while(!(se.equals("o")||se.equals("n"))){
				System.out.println("RŽpondre avec 'o' ou 'n'.");
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
