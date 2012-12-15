package trajet;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import membre.Membre;

public class Trajet {
	protected static int lastID;
	protected int id;
	protected String villeDepart;
	protected String villeArrivee;
	protected GregorianCalendar dateDepart;
	protected int nbPlaces;
	protected Membre conducteur;
	protected ArrayList<Membre> passagers;
	
	static{
		lastID=0;
	}

	/**
	 * @param villeDepart
	 * @param villeArrivee
	 * @param dateDepart
	 * @param nbPlaces
	 * @param conducteur
	 * @param passagers
	 */
	public Trajet(String villeDepart, String villeArrivee,
			GregorianCalendar dateDepart, int nbPlaces, Membre conducteur,
			ArrayList<Membre> passagers) {
		super();
		this.id = ++lastID;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateDepart = dateDepart;
		this.nbPlaces = nbPlaces;
		this.conducteur = conducteur;
		this.passagers = new ArrayList<Membre>();
	}
	
	public boolean addPassager(Membre p){
		// TODO
		return false;
	}

	@Override
	public String toString() {
		String retour = "Trajet n°"+id+" de "+villeDepart+" à "+villeArrivee+", départ le "+dateDepart;
		if(nbPlaces<=passagers.size())
			retour+=", "+(nbPlaces-passagers.size())+" places disponibles";
		else
			retour+=", plus de places disponibles";
		if(conducteur!=null)
			retour+=", conducteur : "+conducteur+".";
		else
			retour+=", en recherche de conducteur.";
		
		return retour;
	}
	
	
}
