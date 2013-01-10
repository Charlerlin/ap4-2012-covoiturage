package covoiturage;

import java.util.Scanner;

import membre.DatabaseMembre;
import membre.Membre;
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
		while(!quitter){
			quitter = menuPrincipal();
		}
		

	}
	
	
	protected static void menuConnexion(){
		// TODO connexion ou inscription
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Connexion au système.");
		System.out.print("Entrez votre pseudo : ");
		String pseudo = sc.nextLine();
		
		Membre m = dbM.rechercheMembre(pseudo);
		if(m!=null){
			membreCourant = m;
		}
		else {
			membreCourant = inscription();
		}
		
	}
	
	protected static boolean menuPrincipal(){
		//TODO
		return false;
	}
	
	protected static Membre inscription(){
		System.out.println("Vous êtes un nouveau membre, merci de vous enregistrer.");
		return Membre.creerMembreConsole();
	}

}
