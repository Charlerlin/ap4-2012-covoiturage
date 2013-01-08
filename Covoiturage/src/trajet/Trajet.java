package trajet;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
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
	
	public static void main(String[] args) {
		Trajet t = Trajet.creerTrajetConducteurConsole(Membre.creerMembreConsole());
		System.out.println(t);
	}

	@Override
	public String toString() {
		String retour = "Trajet n°"+id+" de "+villeDepart+" à "+villeArrivee+", départ le "+dateDepart;
		if(nbPlaces>=passagers.size())
			retour+=", "+(nbPlaces-passagers.size())+" places disponibles";
		else
			retour+=", plus de places disponibles";
		if(conducteur!=null){
			retour+=", conducteur : "+conducteur+", véhicule : "+vehicule+", ";
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
	
	public static Trajet creerTrajetConducteurConsole(Membre membreCourant){
		System.out.println("Créatin d'un nouveau trajet.");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ville de départ : ");
		String villeDepart = sc.nextLine();
		System.out.print("Ville d'arrivée : ");
		String villeArrivee = sc.nextLine();
		System.out.print("Date de départ (jj/mm/aaaa) : ");
		String dateDepart = sc.nextLine();
		// TODO parser la date
		int nbPlaces=0;
		boolean nbPlaceOK = false;
		while(!nbPlaceOK){
			System.out.print("Nombre de places proposées : ");
			try{
				nbPlaces = sc.nextInt();
				nbPlaceOK = true;
				sc.nextLine();
			}
			catch(InputMismatchException e){
				System.out.println("L'entrée n'est pas correcte, merci d'entrer un nombre (sans espaces)");
				//sc = new Scanner(System.in);
				sc.nextLine();
			}
			
		}
		
		System.out.print("Véhicule : ");
		String vehicule = sc.nextLine();
		System.out.print("Acceptez vous les grands bagages ? (o/n) : ");
		String grandBagages = sc.nextLine();
		while(!(grandBagages.equals("o")||grandBagages.equals("n"))){
			System.out.println("Répondre avec 'o' ou 'n'.");
			System.out.print("Acceptez vous les grands bagages ? (o/n) : ");
			grandBagages = sc.nextLine();
		}
		boolean placeBagages;
		if(grandBagages.equals("o")){
			placeBagages = true;
		}
		else{
			placeBagages = false;
		}
		
		Trajet t = new Trajet(villeDepart, villeArrivee, null, nbPlaces, membreCourant, vehicule, placeBagages, null);
		
		return t;
	}
	
	public static Trajet creerTrajetSouhaitConsole(){
		System.out.println("Créatin d'un nouveau trajet.");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ville de départ : ");
		String villeDepart = sc.nextLine();
		System.out.print("Ville d'arrivée : ");
		String villeArrivee = sc.nextLine();
		System.out.print("Date de départ souhaitée (jj/mm/aaaa) : ");
		String dateDepart = sc.nextLine();
		
		//TODO
		
		return null;
	}
	
	
}
