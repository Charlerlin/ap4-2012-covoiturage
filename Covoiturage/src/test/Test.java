package test;

import membre.DatabaseMembre;
import membre.Membre;
import membre.Preferences;
import trajet.Trajet;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseMembre membres = new DatabaseMembre();

		Preferences preferences = new Preferences();
		Membre membre1 = new Membre("supercharles", "Charles", "charles@mail.com", "0625434470", preferences);

		membres.addMembre(membre1);

		System.out.println(membre1.toStringLong());

		System.out.println(membres);

		Trajet trajet1 = new Trajet("Lille", "Paris", null, 3, membre1,"Supercar", true, null);

		System.out.println(trajet1);

		System.out.println(trajet1.hasConducteur());

		System.out.println("\\\\");
	}

}
