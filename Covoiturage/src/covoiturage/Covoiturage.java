package covoiturage;

import java.util.ArrayList;
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
		ajoutFaux();
		menuConnexion();
		System.out.println("\nBonjour " + membreCourant.getNom() + ".\n");
		boolean quitter = false;
		// Ce boolean permet d'afficher la liste des commandes lors du premier affichage du menu
		boolean prems = true;
		while (!quitter) {
			quitter = menuPrincipal(prems);
			prems = false;
		}

	}

	protected static void menuConnexion() {
		System.out.println("Connexion au système.");
		String pseudo = entreeNonVide("Entrez votre pseudo : ");
		Membre m = dbM.rechercherMembrePseudo(pseudo);

		if (m != null) {
			membreCourant = m;
		} else {
			membreCourant = inscription(pseudo);
		}

	}

	protected static Membre inscription(String pseudo) {
		System.out.println("Vous êtes un nouveau membre, merci de vous enregistrer.");
		return Membre.creerMembreConsole(pseudo);
	}

	protected static boolean menuPrincipal(boolean prems) {
		if (prems) {
			System.out.println("Tapez 'aide' pour voir le détail des commandes.");
			System.out.println("Commandes disponibles : trajets, membre, aide (?), quitter.");
			System.out.println("Que voulez vous faire ?");
		}
		System.out.print(">");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if (choix.equals("?") || choix.equals("aide")) {
			System.out.println("Aide sur les commandes.\nLes commandes sont indiquées en début de ligne et le raccourci entre parenthèses.");
			System.out.println("aide\t\t(?)\tAfficher cette aide.");
			System.out.println("trajets\t\t(t)\tAccéder au menu Trajets.");
			System.out.println("membre\t\t(m)\tAccéder au menu Membre.");
			System.out.println("quitter\t\t(q)\tQuitter.");
		}
		//Début des options du menu
		if(choix.equals("t") || choix.equals("trajets")){
			menuTrajets();
		}
		if(choix.equals("m") || choix.equals("membre")){
			menuMembre();
		}

		if (choix.equals("q") || choix.equals("quitter")) {
			return true;
		}

		return false;
	}

	protected static void menuTrajets(){
		System.out.println("Menu Trajets. Tapez 'aide' pour voir le détail des commandes.");
		System.out.println("Commandes disponibles : creer, rechercher, afficher, aide.");
		System.out.print(">>");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if (choix.equals("?") || choix.equals("aide")) {
			System.out.println("aide\t\t(?)\tAfficher cette aide.");
			System.out.println("creer\t\t(c)\tAccéder au menu de création de trajet.");
			System.out.println("rechercher\t(r)\tAccéder au menu de recherche.");
			System.out.println("afficher\t(a)\tAfficher tous les trajets.");
			System.out.print(">>");
			choix = sc.nextLine();
		}
		//Début des options du menu
		if(choix.equals("c") || choix.equals("creer")){
			menuCreationTrajet();
		}
		if(choix.equals("r") || choix.equals("rechercher")){
			menuRechercherTrajet();
		}
		if(choix.equals("a") || choix.equals("afficher")){
			System.out.println(dbT.toString());
		}
	}

	protected static void menuCreationTrajet(){
		System.out.println("Vous êtes conducteur et proposez un trajet, tapez 'a'.");
		System.out.println("Vous êtes passagez et recherchez un trajet, tapez 's'.");
		System.out.print(">>");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		if(choix.equals("a")){
			Trajet t = Trajet.creerTrajetConducteurConsole(membreCourant);
			dbT.addTrajet(t);
		}
		if(choix.equals("s")){
			ajoutTrajetSansConducteur(); 
		}
	}

	protected static void menuRechercherTrajet(){
		System.out.println("Entrez les détails du trajet, nous recherchons les trajets similaires");
		Trajet t = Trajet.creerTrajetSouhaitConsole(membreCourant);
		boolean avecConducteur = false;
		ArrayList<Trajet> trajets = dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur);

		//TODO à terminer
	}

	protected static void ajoutTrajetSansConducteur(){
		System.out.println("Entrez les détails de votre trajet, nous recherchons un trajet similaire avec conducteur.");
		Trajet t = Trajet.creerTrajetSouhaitConsole(membreCourant);
		boolean avecConducteur = false;
		ArrayList<Trajet> trajets = dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur);

		if(trajets.size()==0){
			dbT.addTrajet(t);
			System.out.println("Aucun trajet avec conducteur ne correspond à votre recherche.");
			System.out.println("Votre souhait de trajet est bien enregistré.");
		}
		else{
			int i = 0;
			for(Trajet tl : trajets){
				System.out.println("Trajet n°"+(++i)+" : "+tl);
			}
			Scanner sc = new Scanner(System.in);
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
					sc.nextLine();
				}
				if(choixT<1||choixT>trajets.size()){
					System.out.println("Choix incorrect !");
					choixTOK = false;
				}

			}

		}
		// TODO à terminer
	}

	protected static void menuMembre(){
		System.out.println("Menu Membres. Tapez 'aide' pour voir le détail des commandes.");
		System.out.println("Commandes disponibles : rechercher, modifier, afficher, aide.");
		System.out.print(">>");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if (choix.equals("?") || choix.equals("aide")) {
			System.out.println("aide\t\t(?)\tAfficher cette aide.");
			System.out.println("rechercher\t(r)\tRechercher un membre par pseudo ou par profil.");
			System.out.println("modifier\t(m)\tAfficher et/ou modifier votre profil.");
			System.out.println("afficher\t(a)\tAfficher tous les membres.");
			System.out.print(">>");
			choix = sc.nextLine();
		}
		//Début des options du menu
		if(choix.equals("r") || choix.equals("rechercher")){
			rechercheMembre();
		}
		if(choix.equals("m") || choix.equals("modifier")){
			menuModifierMembre();
		}
		if(choix.equals("a") || choix.equals("afficher")){
			System.out.println(dbM.toString());
		}
	}


	protected static void rechercheMembre(){
		System.out.println("Entrez le nom ou le pseudo du membre que vous recherchez.");
		System.out.print(">>>");
		String entry = entreeNonVide("");

		Membre resPseudo = dbM.rechercherMembrePseudo(entry);
		ArrayList<Membre> resNom = dbM.rechercherMembresNom(entry);

		if(resPseudo==null && resNom.size()==0){
			System.out.println("Votre recherche n'a renvoyé aucun résultat");
		}
		else{
			if(resPseudo==null)
				System.out.println("Aucun membre ne porte le pseudo : '"+entry+"'.");
			else
				System.out.println("Résultat de la recherche par pseudo : \n"+resPseudo.toStringLong());
			if(resNom.size()!=0){
				System.out.println("Résulat de la recherche de membres dont le nom contient '"+entry+"'.");
				System.out.println(resNom);
				System.out.println("Vous pouvez afficher plus d'informations sur le membre en recherchant directement son pseudo.");
			}
		}
		// TODO
	}

	protected static void menuModifierMembre(){
		//TODO faire l'affichage des trajets auxquels participe le membre, en tant que conducteur et/ou passager.
		System.out.println("Afficher (a) ou éditer (e) le profil ?");
		System.out.print(">>>");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		if (choix.equals("a") || choix.equals("afficher"))
			System.out.println("Voici votre profil : ");
		System.out.println(membreCourant.toStringLong());
		if (choix.equals("e") || choix.equals("editer"))
			membreCourant.editMembreConsole();
	}

	public static String entreeNonVide(String inviteDeChamp){
		String retour = "";
		Scanner sc = new Scanner(System.in);
		while(retour.isEmpty()){
			System.out.print(inviteDeChamp);
			retour = sc.nextLine();
			if(retour.isEmpty())
				System.out.println("Le champ ne peut être vide, recommencez.");
		}
		return retour.trim();
	}

	protected static void ajoutFaux(){
		Preferences preferences = new Preferences();
		
		Membre jecree = new Membre("charles", "Charles Herlin", "charles@mail.com", "06", preferences);
		dbM.addMembre(jecree);
		jecree = new Membre("supermenteur", "Jacques Chirac", "jchirac@laposte.net", "0681851515", preferences);
		dbM.addMembre(jecree);
		jecree = new Membre("nainposteur", "Nicolas Sarkozy", "contact@nicolas-sarkozy-2017.fr", "06548151", preferences);
		dbM.addMembre(jecree);
		jecree = new Membre("mecnormal", "Francois Hollande", "contact@elysee.fr", "06518115158", preferences);
		dbM.addMembre(jecree);
		jecree = new Membre("bleumarine", "Marine Le Pen", "contact@lepen2002-7-12-17.fr", "0651845458", preferences);
		dbM.addMembre(jecree);
		
		Trajet t = new Trajet("Lille", "Paris", Trajet.genererDateTime("16-01-2013 08:20"), 3, dbM.rechercherMembrePseudo("charles"), "Clio", true, null);
		dbT.addTrajet(t);
	}
}
