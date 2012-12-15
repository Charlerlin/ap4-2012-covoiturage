package membre;

import java.util.ArrayList;

public class DatabaseMembre {
	protected ArrayList<Membre> listeMembres;
	
	public DatabaseMembre(){
		listeMembres = new ArrayList<Membre>();
	}
	
	public void addMembre(Membre m){
		listeMembres.add(m);
	}

	@Override
	public String toString() {
		String retour = "Liste des memebres : ";
		for(Membre m : listeMembres){
			retour+="\n"+m.toStringLong();
		}
		return retour;
	}
	
	
}
