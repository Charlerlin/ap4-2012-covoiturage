package trajet;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import membre.Membre;

public class Trajet {
	protected static int lastID;
	protected int id;
	protected String villeDepart;
	protected String villeArrivee;
	protected GregorianCalendar dateDepart;
	protected int nbPlaces;
	protected Membre conducteur;
	protected String vehicule;
	protected boolean placeGrandBagages;
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
	public Trajet(String villeDepart, 
			String villeArrivee,
			GregorianCalendar dateDepart, 
			int nbPlaces, 
			Membre conducteur,
			String vehicule,
			boolean placeBagages,
			ArrayList<Membre> passagers) {
		super();
		this.id = ++lastID;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateDepart = dateDepart;
		this.nbPlaces = nbPlaces;
		this.conducteur = conducteur;
		this.vehicule = vehicule;
		this.placeGrandBagages = placeBagages;
		this.passagers = new ArrayList<Membre>();
	}
	
	public boolean addPassager(Membre p){
		if(nbPlaces>passagers.size()){
			passagers.add(p);
			return true;
		}
		return false;
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
		String retour = "Trajet n�"+id+" de "+villeDepart+" � "+villeArrivee+", d�part le "+dateDepart;
		if(nbPlaces>=passagers.size())
			retour+=", "+(nbPlaces-passagers.size())+" places disponibles";
		else
			retour+=", plus de places disponibles";
		if(conducteur!=null){
			retour+=", conducteur : "+conducteur+", v�hicule : "+vehicule+", ";
			if(placeGrandBagages){
				retour+="place pour grands bagages.";
			}
			else{
				retour+="pas de place pour grands bagages.";
			}
		}
		else
			retour+=", en recherche de conducteur.";
		
		return retour;
	}
	
	public static Trajet creerTrajetConsole(){
		System.out.println("Cr�atin d'un nouveau trajet.");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ville de d�part : ");
		String villeDepart = sc.nextLine();
		System.out.print("Ville d'arriv�e : ");
		String villeArrivee = sc.nextLine();
		System.out.print("Date de d�part (jj/mm/aaaa) : ");
		String dateDepart = sc.nextLine();
		System.out.print("Nombre de places propos�es : ");
		String nbPlaces = sc.nextLine();
		System.out.print("Conducteur : ");
		String conducteur = sc.nextLine();
		System.out.print("V�hicule : ");
		String vehicule = sc.nextLine();
		System.out.print("Acceptez vous les grands bagages ? (o/n) : ");
		String grandBagages = sc.nextLine();
		
		//TODO
		
		return null;
	}
	
	
}
