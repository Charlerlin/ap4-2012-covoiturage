package covoiturage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import membre.DatabaseMembre;
import membre.Membre;
import membre.Preferences;
import trajet.DatabaseTrajet;
import trajet.Trajet;

public class Covoiturage {

	protected static DatabaseTrajet dbT;
	protected static DatabaseMembre dbM;

	protected static Membre membreCourant;

	static {
		dbT = new DatabaseTrajet();
		dbM = new DatabaseMembre();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		menuConnexion();
		System.out.println("\nBonjour " + membreCourant.getNom() + ".\n");
		boolean quitter = false;
		// Ce boolean permet d'afficher la liste des commandes lors du premier
		// affichage du menu
		boolean prems = true;
		while (!quitter) {
			quitter = menuPrincipal(prems);
			prems = false;
		}

	}

	protected static void menuConnexion() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Connexion au système.");
		System.out.print("Entrez votre pseudo : ");
		String pseudo = sc.nextLine();

		Membre m = dbM.rechercheMembre(pseudo);

		// POUR TEST UNIQUEMENT TODO
		Preferences preferences = new Preferences();
		m = new Membre("supercharles", "Charles", "charles@mail.com",
				"0625434470", preferences);
		//
		if (m != null) {
			membreCourant = m;
		} else {
			membreCourant = inscription();
		}

	}

	protected static boolean menuPrincipal(boolean prems) {
		if (prems) {
			System.out
					.println("Tapez 'aide' pour voir le détail des commandes.");
			System.out
					.println("Commandes disponibles : creer, rechercher, afficher, profil, aide (h), quitter.");
			System.out.println("Que voulez vous faire ?");
		}
		System.out.print(">");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if (choix.equals("h") || choix.equals("aide")) {
			System.out
					.println("Aide sur les commandes.\nLes commandes sont indiquées en début de ligne et le raccourci entre parenthèses.");
			System.out.println("aide\t\t(h)\tAfficher cette aide.");
			System.out.println("creer\t\t(c)\tCréer un nouveau trajet.");
			System.out.println("rechercher\t(r)\tRechercher un trajet.");
			System.out.println("afficher\t(a)\tAfficher tous les trajts.");
			System.out.println("profil\t\t(p)\tAfficher ou éditer le profil.");
			System.out.println("quitter\t\t(q)\tQuitter.");
		}
		if (choix.equals("c") || choix.equals("creer")) {
			System.out.println("Vous êtes conducteur et proposez un trajet, tapez 'a'.");
			System.out.println("Vous êtes passagez et recherchez un trajet, tapez 's'.");
			System.out.print(">>");
			choix = sc.nextLine();
			if(choix.equals("a")){
				Trajet t = Trajet.creerTrajetConducteurConsole(membreCourant);
				dbT.addTrajet(t);
			}
			if(choix.equals("s")){
				System.out.println("Entrez les détails de votre trajet, nous recherchons un trajet similaire avec conducteur.");
				Trajet t = Trajet.creerTrajetSouhaitConsole(membreCourant);
				boolean avecConducteur = false;
				ArrayList<Trajet> trajets = dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur);
				
				if(trajets.size()==0){
					dbT.addTrajet(t);
					System.out.println("Aucun trajet avec conducteur ne correspond à votre recherche.");
					System.out.println("Votre souhait de trajet est bien enregistré, nous vous contacterons dès qu'un conducteur proposera un trajet similaire.");
				}
				else{
					int i = 0;
					for(Trajet tl : trajets){
						System.out.println("Trajet n°"+(++i)+" : "+tl);
					}
					int choixT = 0;
					boolean choixTOK = false;
					while(!choixTOK){
						System.out.print("Numéro du trajet qui vous intéresse : ");
						try{
							choixT = sc.nextInt();
							choixTOK = true;
							sc.nextLine();
						}
						catch(InputMismatchException e){
							System.out.println("L'entrée n'est pas correcte, merci d'entrer un nombre (sans espaces)");
							//sc = new Scanner(System.in);
							sc.nextLine();
						}
						if(choixT<1||choixT>trajets.size()){
							System.out.println("Choix incorrect !");
							choixTOK = false;
						}
						
					}
					
				}
			}
		}
		if (choix.equals("r") || choix.equals("rechercher")) {

		}
		if (choix.equals("a") || choix.equals("afficher")) {
			System.out.println(dbT.toString());
		}
		if (choix.equals("p") || choix.equals("profil")) {
			System.out.println("Afficher (a) ou éditer (e) le profil ?");
			System.out.print(">>");
			choix = sc.nextLine();
			if (choix.equals("a") || choix.equals("afficher"))
				System.out.println(membreCourant.toStringLong());
			if (choix.equals("e") || choix.equals("editer"))
				membreCourant.editMembreConsole();
		}
		if (choix.equals("q") || choix.equals("quitter")) {
			return true;
		}

		return false;
	}

	protected static Membre inscription() {
		System.out
				.println("Vous êtes un nouveau membre, merci de vous enregistrer.");
		return Membre.creerMembreConsole();
	}

}
