package trajet;

import java.util.ArrayList;

import membre.Membre;

import org.joda.time.DateTime;

/** Gestion des trajets, et recherche
 * 
 * @author charlerlin
 *
 */
public class DatabaseTrajet {
	protected ArrayList<Trajet> listeTrajets;

	/**Création d'une base de trajets vide
	 * 
	 */
	public DatabaseTrajet(){
		listeTrajets = new ArrayList<Trajet>();
	}

	/**Ajout d'un trajet à la base
	 * 
	 * @param t
	 */
	public void addTrajet(Trajet t){
		listeTrajets.add(t);
	}

	/**Recherche de trajets correspondant aux données passées en paramètres.
	 * 
	 * @param villeDepart
	 * @param villeArrivee
	 * @param dateDepart
	 * @param avecConducteur
	 * @param avecPlacesLibres
	 * @return Une liste des trajets, vide si aucun n'a été trouvé
	 */
	public ArrayList<Trajet> rechercheTrajet(String villeDepart, 
			String villeArrivee,
			DateTime dateDepart,
			boolean avecConducteur,
			boolean avecPlacesLibres){
		ArrayList<Trajet> resultat = new ArrayList<Trajet>();

		for(Trajet t : listeTrajets){
			if(villeDepart.equalsIgnoreCase(t.getVilleDepart()) &&
					villeArrivee.equalsIgnoreCase(t.getVilleArrivee()) &&
					t.dateConcorde(dateDepart) &&
					avecConducteur==t.hasConducteur() &&
					avecPlacesLibres==t.hasPlacesLibres()){
				resultat.add(t);
			}
		}
		return resultat;
	}
	
	/**Recherche d'un trajet par son id (unique)
	 * 
	 * @param id
	 * @return le trajet correspondant, null si aucun trajet correspondant
	 */
	public Trajet getTrajetByID(int id){
		for(Trajet t : listeTrajets){
			if(id==t.getID())
				return t;
		}
		return null;
	}
	
	/**Retourne la liste des trajets dont le membre passé en paramètre est conducteur
	 * 
	 * @param m Membre dont on recherche les trajet
	 * @return la liste des trajets dont le membre est conducteur, vide si aucun
	 */
	public ArrayList<Trajet> getTrajetByConducteur(Membre m){
		ArrayList<Trajet> res = new ArrayList<Trajet>();
		for(Trajet t : listeTrajets){
			if(m==t.getConducteur())
				res.add(t);
		}
		return res;
	}
	
	/**Retourne la liste des trajets dont le membre passé en paramètre est passager
	 * 
	 * @param m Membre dont on recherche les trajet
	 * @return la liste des trajets dont le membre est passager, vide si aucun
	 */
	public ArrayList<Trajet> getTrajetsWithPassager(Membre m){
		ArrayList<Trajet> res = new ArrayList<Trajet>();
		for(Trajet t : listeTrajets){
			if(t.hasMembreAsPassager(m)){
				res.add(t);
			}
		}
		return res;
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
