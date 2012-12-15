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
		this.passagers = passagers;
	}
	
	public boolean addPassager(Membre p){
		// TODO
		return false;
	}

	@Override
	public String toString() {
		return "Trajet [id=" + id + ", villeDepart=" + villeDepart
				+ ", villeArrivee=" + villeArrivee + ", dateDepart="
				+ dateDepart + ", nbPlaces=" + nbPlaces + ", conducteur="
				+ conducteur + ", passagers=" + passagers + "]";
	}
	
	
}
