package trajet;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
			GregorianCalendar dateDepart,
			boolean avecConducteur){
		//TODO
		return null;
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
			retour = "Il n'y a encore aucun trajet enregistrŽ.";
		}
		return retour;
	}
}
