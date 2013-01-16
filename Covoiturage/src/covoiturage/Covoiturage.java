package covoiturage;



import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import membre.DatabaseMembre;
import membre.Membre;
import membre.Preferences;
import trajet.DatabaseTrajet;
import trajet.Trajet;

/**Classe principale du système de covoiturage qui gère tout le dialogue console et les appels aux fonctions des autres classes
 * @author charlerlin
 *
 */
public class Covoiturage {

	protected static DatabaseTrajet dbT;
	protected static DatabaseMembre dbM;

	protected static Membre membreCourant;

	static {
		dbT = new DatabaseTrajet();
		dbM = new DatabaseMembre();
	}

	/**Fonction principale du système de covoiturage qui appelle toutes les autres
	 * @param args
	 */
	public static void main(String[] args) {
		ajoutFaux();
		menuConnexion();
		System.out.println("\nBonjour " + membreCourant.getNom() + ".\n");
		boolean quitter = false; //boolean permettant de relancer le menu tant que l'uilisateur ne veut pas quitter
		boolean prems = true; // Ce boolean permet d'afficher la liste des commandes lors du premier affichage du menu
		while (!quitter) {
			quitter = menuPrincipal(prems);
			prems = false;
		}

	}

	/**Menu de connexin qui vérifie si le pseudo existe, sinon, invoque la création d'un nouveau membre
	 * Chargé de la gestion de l'unicité des pseudos
	 */
	protected static void menuConnexion() {
		System.out.println("Connexion au système.");
		String pseudo = entreeNonVide("Entrez votre pseudo : ");
		Membre m = dbM.rechercherMembrePseudo(pseudo);

		if(m != null) {
			membreCourant = m;
		} 
		else {
			membreCourant = inscription(pseudo);
		}

	}


	/**Création d'un nouveau membre, invoque la création d'un membre dans la console
	 * @param pseudo pseudo unique (normalement) passé en paramètre
	 * @return un membre avec le pseudo passé en paramètre
	 */
	protected static Membre inscription(String pseudo) {
		System.out.println("Vous êtes un nouveau membre, merci de vous enregistrer.");
		return Membre.creerMembreConsole(pseudo);
	}

	/**Menu principal
	 * @param prems vrai si premier affichage du menu
	 * @return false si l'utilisateur veut quitter, vrai sinon
	 */
	protected static boolean menuPrincipal(boolean prems) {
		if (prems) {
			System.out.println("Tapez 'aide' ou '?' pour voir le détail des commandes.");
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

	/**Menu principal pour la gestion des trajets
	 * 
	 */
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

	/**Menu de création de trajet avec ou sans conducteur qui invoque les méthodes correspondantes en console en passant le membre courant en paramètre
	 * 
	 */
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

	/**Menu de recherche de trajet
	 * 
	 */
	protected static void menuRechercherTrajet(){
		System.out.println("Entrez les détails du trajet, nous recherchons les trajets similaires");
		Trajet t = Trajet.creerTrajetSouhaitConsole(membreCourant); //création d'un trajet qui va servir de base de comparaison
		boolean avecConducteur = true; //attributs du trajet non définis par l'assitant
		boolean avecPlacesLibres = true;
		ArrayList<Trajet> trajets = dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur, avecPlacesLibres);
		avecPlacesLibres = false; //permet de rechercher tous les trajets correspondants
		trajets.addAll(dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur, avecPlacesLibres));

		if(trajets.size()==0){
			System.out.println("Aucun trajet avec conducteur ne correspond à votre recherche.");
		}
		else{
			System.out.println("Voici tous les trajets correspondant à votre recherche : ");
			for(Trajet tl : trajets){
				System.out.println(tl);
			}
		}
		System.out.println("Pour vous inscrire en tant que passager dans un trajet ou créer un nouveau souhait de trajet, veuillez passer par le menu Trajet > Ajouter > Sans conducteur.");
	}

	/**Dialogue console permettant d'ajouter un trajet sans conducteur ou proposant de s'inscrire à un trajet si des trajets correspondants existent déjà
	 * 
	 */
	protected static void ajoutTrajetSansConducteur(){
		System.out.println("Entrez les détails de votre trajet, nous recherchons un trajet similaire avec conducteur.");
		Trajet t = Trajet.creerTrajetSouhaitConsole(membreCourant); //création d'un trajet pour base de comparaison
		boolean avecConducteur = true; //attributs du trajets non définis par l'assistant
		boolean avecPlacesLibres = true;
		ArrayList<Trajet> trajets = dbT.rechercheTrajet(t.getVilleDepart(), t.getVilleArrivee(), t.getDateDepart(), avecConducteur, avecPlacesLibres);

		if(trajets.size()==0){
			dbT.addTrajet(t);
			System.out.println("Aucun trajet avec conducteur ne correspond à votre recherche.");
			System.out.println("Votre souhait de trajet est bien enregistré.");
		}
		else{
			for(Trajet tl : trajets){
				System.out.println(tl); //liste les trajets trouvés
			}
			Scanner sc = new Scanner(System.in);
			int choixT = 0;
			boolean choixTOK = false;
			while(!choixTOK){//entrée d'un choix de trajet par le numéro
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
				if(choixT<1||!numeroTrajetOK(choixT, trajets)){//vérification de la validité du choix par rapport aux trajets de la liste
					System.out.println("Choix incorrect !");
					choixTOK = false;
				}

			}
			Trajet tSelect = dbT.getTrajetByID(choixT);
			System.out.println("Voici le trajet que vous avez sélectionné : \n"+tSelect);
			System.out.print("Êtes vous certain de vouloir participer à ce trajet ? (o/n) : ");
			String confirmation = sc.nextLine();
			while(!(confirmation.equals("o")||confirmation.equals("n"))){//demande de confirmation avant d'ajouter le membre au trajet sélectionné
				System.out.println("Répondre avec 'o' ou 'n'.");
				System.out.print("Êtes vous certain de vouloir participer à ce trajet ? (o/n) : ");
				confirmation = sc.nextLine();
			}
			if(confirmation.equals("o")){//tentative ajout du membre
				if(tSelect.addPassager(membreCourant))
					System.out.println("Vous avez bien été ajouté au trajet");
				else
					System.out.println("Vous n'avez pas pu être ajouté au trajet, peut-être ne reste-t-il plus suffisamment de place.");

			}
		}//fin bloc de sélection de trajet
	}

	/**Vérifie si un trajet de la liste fournie porte bien l'id passé en paramètre
	 * @param n id à rechercher
	 * @param trajets liste des trajets dans laquelle rechercher
	 * @return true si le numéro a été trouvé, false sinon
	 */
	protected static boolean numeroTrajetOK(int n, ArrayList<Trajet> trajets){
		for(Trajet t : trajets){
			if(n==t.getID()){
				return true;
			}
		}
		return false;
	}

	/**Dialogue console pour la gestion du membre courant
	 * 
	 */
	protected static void menuMembre(){
		System.out.println("Menu Membres. Tapez 'aide' pour voir le détail des commandes.");
		System.out.println("Commandes disponibles : rechercher, modifier, trajets, afficher, aide.");
		System.out.print(">>");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if (choix.equals("?") || choix.equals("aide")) {
			System.out.println("aide\t\t(?)\tAfficher cette aide.");
			System.out.println("rechercher\t(r)\tRechercher un membre par pseudo ou par profil.");
			System.out.println("modifier\t(m)\tAfficher et/ou modifier votre profil.");
			System.out.println("trajets\t\t(t)\tRechercher les trajets auxquels vous participez.");
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
		if(choix.equals("t")||choix.equals("trajets")){
			rechercheTrajetsParticipant(membreCourant);
		}
		if(choix.equals("a") || choix.equals("afficher")){
			System.out.println(dbM.toString());
		}
	}


	/**Dialogue et mise en forme de la recherche de membre
	 * Fait appel à la recherche par pseudo (unique) et par correspondance de nom.
	 */
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
	}

	/**Dialogue permettant de choisir entre affichage et édition du membre courant
	 * appel à la fonction d'édition en console le cas échéant
	 */
	protected static void menuModifierMembre(){
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

	/**Recherche et affichage des trajets dans lesquels le membre passé en paramètre participe
	 * @param m membre à rechercher
	 */
	protected static void rechercheTrajetsParticipant(Membre m){
		ArrayList<Trajet> resConducteur = dbT.getTrajetByConducteur(m);
		ArrayList<Trajet> resPassager = dbT.getTrajetsWithPassager(m);
		
		System.out.println();
		if(resConducteur.size()==0 && resPassager.size()==0){
			System.out.println("Vous ne participez encore à aucun trajet");
		}
		else{
			if(resConducteur.size()!=0){
				System.out.println("Vous participez en tant que conducteur aux trajets suivants : ");
				for(Trajet tl : resConducteur){
					System.out.println(tl);
				}
			}
			else{
				System.out.println("Vous ne participez encore à aucun trajet en tant que conducteur.");
			}
			System.out.println();
			if(resPassager.size()!=0){
				System.out.println("Vous participez en tant que passager aux trajets suivants : ");
				for(Trajet tl : resPassager){
					System.out.println(tl);
				}
			}
			else{
				System.out.println("Vous ne participez encore à aucun trajet en tant que passager");
			}
			System.out.println();
		}
	}

	/**Fonction utilitaire ne permettant pas de renvoyer une string vide
	 * @param inviteDeChamp string à afficher dans la console en tant que prompt
	 * @return une string oblogatoirement non vide
	 */
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

	/**Fonction utilitaire permettant de peupler les bases de données membres et trajets
	 * 
	 */
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

		ArrayList<Membre> passagers = new ArrayList<Membre>();
		passagers.add(dbM.rechercherMembrePseudo("nainposteur"));
		Trajet t = new Trajet("Lille", "Paris", Trajet.genererDateTime("18-01-2013 08:20"), 3, dbM.rechercherMembrePseudo("charles"), "Clio", true, passagers);
		dbT.addTrajet(t);
		t = new Trajet("Paris", "Lille", Trajet.genererDateTime("19-01-2013 18:00"), 0, null, "", false, passagers);
		dbT.addTrajet(t);
	}
}
