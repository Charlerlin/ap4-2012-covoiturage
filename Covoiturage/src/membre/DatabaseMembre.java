package membre;

import java.util.ArrayList;

/**Gestion des membres, et recherche
 * @author charlerlin
 *
 */
public class DatabaseMembre {
	protected ArrayList<Membre> listeMembres;
	
	public static void main(String[] args) {
		
	}

	/**Création d'une base de membres vide
	 * 
	 */
	public DatabaseMembre(){
		listeMembres = new ArrayList<Membre>();
	}

	/**Ajout du membre passé en paramètre à la base
	 * @param m
	 */
	public void addMembre(Membre m){
		listeMembres.add(m);
	}

	/**Recherche d'un membre par son pseudo (unicité)
	 * @param pseudo pseudo du membre à rechercher
	 * @return le membre correspondant, null sinon
	 */
	public Membre rechercherMembrePseudo(String pseudo){
		for(Membre m : listeMembres){
			if(pseudo.trim().equalsIgnoreCase((m.getPseudo())))
				return m;
		}
		return null;
	}

	/**Recherche des membres correspondants à la chaine de caractères passée en paramètres
	 * @param nom chaine de caractère à rechercher, pas nécessairement un nom en intégralité
	 * @return la liste des membres correspondants, vide sinon
	 */
	public ArrayList<Membre> rechercherMembresNom(String nom){
		ArrayList<Membre> retour = new ArrayList<Membre>();
		for(Membre m : listeMembres){
			if(m.getNom().toLowerCase().indexOf(nom.toLowerCase().trim())!=-1)
				retour.add(m);
		}
		return retour;
	}

	@Override
	public String toString() {
		String retour = "Liste des membres : ";
		for(Membre m : listeMembres){
			retour+="\n"+m.toStringLong();
		}
		return retour;
	}


}
