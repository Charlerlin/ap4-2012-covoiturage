package covoiturage;

import java.util.Scanner;

import membre.DatabaseMembre;
import membre.Membre;
import membre.Preferences;
import trajet.DatabaseTrajet;



public class Covoiturage {

	protected static DatabaseTrajet dbT;
	protected static DatabaseMembre dbM;

	protected static Membre membreCourant;

	static{
		dbT = new DatabaseTrajet();
		dbM = new DatabaseMembre();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		menuConnexion();
		System.out.println("Bonjour "+membreCourant.getNom()+".");
		boolean quitter = false;
		boolean prems=true;
		while(!quitter){
			quitter = menuPrincipal(prems);
			prems = false;
		}


	}


	protected static void menuConnexion(){
		// TODO connexion ou inscription
		Scanner sc = new Scanner(System.in);

		System.out.println("Connexion au syst�me.");
		System.out.print("Entrez votre pseudo : ");
		String pseudo = sc.nextLine();

		Membre m = dbM.rechercheMembre(pseudo);
		
		//test TODO
		Preferences preferences = new Preferences();
		m = new Membre("supercharles", "Charles", "charles@mail.com", "0625434470", preferences);
		//
		if(m!=null){
			membreCourant = m;
		}
		else {
			membreCourant = inscription();
		}

	}

	protected static boolean menuPrincipal(boolean prems){
		if(prems){
			System.out.println("Tapez 'aide' pour voir le d�tail des commandes");
			System.out.println("Commandes disponibles : creer, rechercher, afficher, profil, aide (h), quitter.");
			System.out.println("Que voulez vous faire ?");
		}
		System.out.print(">");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();

		if(choix.equals("h")||choix.equals("aide")){
			System.out.println("Aide sur les commandes.\nLes commandes sont indiqu�es en d�but de ligne et le raccourci entre parenth�ses.");
			System.out.println("aide\t\t(h)\tAfficher cette aide.");
			System.out.println("creer\t\t(c)\tCr�er un nouveau trajet.");
			System.out.println("rechercher\t(r)\tRechercher un trajet.");
			System.out.println("afficher\t(a)\tAfficher tous les trajts.");
			System.out.println("profil\t\t(p)\tAfficher ou �diter le profil.");
			System.out.println("quitter\t\t(q)\tQuitter.");
		}
		if(choix.equals("c")||choix.equals("creer")){
			
		}
		if(choix.equals("r")||choix.equals("rechercher")){
			
		}
		if(choix.equals("a")||choix.equals("afficher")){
			System.out.println(dbT.toString());
		}
		if(choix.equals("p")||choix.equals("profil")){
			System.out.println("Afficher (a) ou �diter (e) le profil ?");
			System.out.print(">>");
			choix = sc.nextLine();
			if(choix.equals("a")||choix.equals("afficher"))
				System.out.println(membreCourant.toStringLong());
			if(choix.equals("e")||choix.equals("editer"))
				membreCourant.editMembreConsole();
		}
		if(choix.equals("q")||choix.equals("quitter")){
			return true;
		}

		return false;
	}

	protected static Membre inscription(){
		System.out.println("Vous �tes un nouveau membre, merci de vous enregistrer.");
		return Membre.creerMembreConsole();
	}

}
