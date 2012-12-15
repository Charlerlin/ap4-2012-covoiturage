package membre;

import java.util.ArrayList;

public class Preferences {
	protected ArrayList<String> preferences;
	
	public Preferences() {
		preferences = new ArrayList<String>();
	}
	
	public void add(String s){
		preferences.add(s);
	}
	
	public String toString(){
		String retour = "";
		for(String s : preferences){
			retour+=s+", ";
		}
		return retour;
	}
}
