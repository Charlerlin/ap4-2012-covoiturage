package trajet;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class DatabaseTrajet {
	protected ArrayList<Trajet> listeTrajets;

	public DatabaseTrajet(){
		listeTrajets = new ArrayList<Trajet>();
	}

	public void addTrajet(Trajet t){
		listeTrajets.add(t);
	}

	public ArrayList<Trajet> rechercheTrajet(String villeDepart, 
			String villeArrivee,
			DateTime dateDepart,
			boolean avecConducteur){
		ArrayList<Trajet> resultat = new ArrayList<Trajet>();

		for(Trajet t : listeTrajets){
			if(villeDepart.equalsIgnoreCase(t.getVilleDepart()) &&
					villeArrivee.equalsIgnoreCase(t.getVilleArrivee()) &&
					t.dateConcorde(dateDepart) &&
					avecConducteur==t.hasConducteur()){
				resultat.add(t);
			}
		}
		return resultat;
	}

	@Override
	public String toString() {
		String retour;
		if(listeTrajets.size()!=0){
			retour = "Liste des trajets : ";
			for(Trajet m : listeTrajets){
				retour+="\n"+m.toString();
			}	
		}
		else{
			retour = "Il n'y a encore aucun trajet enregistré.";
		}
		return retour;
	}

}
