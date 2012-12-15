package membre;

import java.util.ArrayList;

public class DatabaseMembre {
	protected ArrayList<Membre> listeMembres;
	
	public DatabaseMembre(){
		
	}
	
	public void addMembre(Membre m){
		listeMembres.add(m);
	}
}
