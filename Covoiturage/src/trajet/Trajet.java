package trajet;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import membre.Membre;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class Trajet {
	protected static int lastID;
	protected int id;
	protected String villeDepart;
	protected String villeArrivee;
	protected DateTime dateDepart;
	protected int nbPlaces;
	protected Membre conducteur;
	protected String vehicule;
	protected boolean placeGrandBagages;
	protected ArrayList<Membre> passagers;

	protected static DateTimeFormatter inDTF;
	protected static DateTimeFormatter outDTF;

	static{
		lastID=0;
		inDTF = DateTimeFormat.forPattern("dd-MM-YYYY HH:mm");
		outDTF = new DateTimeFormatterBuilder()
		.appendLiteral("le ")
		.appendDayOfMonth(2)
		.appendLiteral(" ")
		.appendMonthOfYearText()
		.appendLiteral(" ")
		.appendYear(4, 4)
		.appendLiteral(" à ")
		.appendHourOfDay(2)
		.appendLiteral("h")
		.appendMinuteOfHour(2)
		.toFormatter();
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
			DateTime dateDepart, 
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

	public static void main(String[] args) {
		//Trajet t = Trajet.creerTrajetConducteurConsole(Membre.creerMembreConsole());
		//System.out.println(t);
		entrerDateConsole();
		//DateTime actuelle = new DateTime();
		//System.out.println(actuelle.toString(outDTF));

	}

	//GETTERS
	public String getVilleDepart() {
		return villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public DateTime getDateDepart() {
		return dateDepart;
	}

	public Membre getConducteur() {
		return conducteur;
	}
	public boolean hasConducteur(){
		return !(conducteur==null);
	}

	public boolean addPassager(Membre p){
		if(nbPlaces>passagers.size()){
			passagers.add(p);
			return true;
		}
		return false;
	}
	
	public boolean dateConcorde(DateTime d){
		if(dateDepart.minusHours(4).isBefore(d) && dateDepart.plusHours(4).isAfter(d))
			return true;
		return false;
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
		System.out.println("Créatin d'un nouveau trajet en tant que conducteur.");
		Scanner sc = new Scanner(System.in);

		System.out.print("Ville de départ : ");
		String villeDepart = sc.nextLine();
		System.out.print("Ville d'arrivée : ");
		String villeArrivee = sc.nextLine();
		System.out.println("Sasie de l'heure et de la date de départ : ");
		DateTime dateDepart = entrerDateConsole();

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

		Trajet t = new Trajet(villeDepart, villeArrivee, dateDepart, nbPlaces, membreCourant, vehicule, placeBagages, null);

		return t;
	}


	public static Trajet creerTrajetSouhaitConsole(Membre membreCourant){
		System.out.println("Créatin d'un nouveau trajet en tant que passager.");
		Scanner sc = new Scanner(System.in);

		System.out.print("Ville de départ : ");
		String villeDepart = sc.nextLine();
		System.out.print("Ville d'arrivée : ");
		String villeArrivee = sc.nextLine();
		System.out.println("Saisie de la date et de l'heure de départ souhaitée : ");
		DateTime dateDepart = entrerDateConsole();

		ArrayList<Membre> passagers = new ArrayList<Membre>();
		passagers.add(membreCourant);

		Trajet t = new Trajet(villeDepart, villeArrivee, dateDepart, 0, null, "", false, passagers);

		return t;
	}

	protected static DateTime entrerDateConsole(){
		Scanner sc = new Scanner(System.in);
		DateTime horaire = null;
		boolean dateOK = false;
		boolean dateFormatOK = false;
		while(!dateOK){
			while(!dateFormatOK){
				System.out.print("Entrer la date de départ (jj-mm-aaaa) : ");
				String date = sc.nextLine();
				System.out.print("Entrer l'heure de départ (hh:mm) : ");
				String heure = sc.nextLine();
				String strHoraire = date+" "+heure;

				try{
					horaire = DateTime.parse(strHoraire, inDTF);
					dateFormatOK = true;
				}
				catch(IllegalArgumentException e){
					System.out.println("Le couple date et heure entré n'est pas valide, merci de respecter le format.");
				}
			}
			if(horaire.isAfterNow()){
				dateOK = true;
			}
			else{
				System.out.println("Le couple date et heure entré n'est pas valide, merci d'entrer une date postérieure à la date actuelle.");
				dateFormatOK = false;
			}
		}
		return horaire;
	}


}
