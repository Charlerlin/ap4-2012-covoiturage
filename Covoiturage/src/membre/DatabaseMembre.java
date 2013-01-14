package membre;

import java.util.ArrayList;

public class DatabaseMembre {
	protected ArrayList<Membre> listeMembres;
	
	public static void main(String[] args) {
		
	}

	public DatabaseMembre(){
		listeMembres = new ArrayList<Membre>();
	}

	public void addMembre(Membre m){
		listeMembres.add(m);
	}

	public Membre rechercherMembrePseudo(String pseudo){
		for(Membre m : listeMembres){
			if(pseudo.equalsIgnoreCase(m.getPseudo()))
				return m;
		}
		return null;
	}
	/*public Membre rechercherMembreNom(String nom){
		for(Membre m : listeMembres){
			if(nom.equalsIgnoreCase(m.getNom()))
				return m;
		}
		return null;
	}*/

	public ArrayList<Membre> rechercherMembresNom(String nom){
		ArrayList<Membre> retour = new ArrayList<Membre>();
		for(Membre m : listeMembres){
			//if(nom.equalsIgnoreCase(m.getNom()))
			if(m.getNom().toLowerCase().indexOf(nom)!=-1)
				retour.add(m);
		}
		return retour;
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
