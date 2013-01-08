package membre;

import java.util.Scanner;


public class Membre {
	protected String pseudo;
	protected String nom;
	protected String email;
	protected String telephone;
	protected Preferences preferences;

	/**
	 * @param nom
	 * @param email
	 * @param telephone
	 * @param preferences
	 */
	public Membre(String pseudo,
			String nom, 
			String email, 
			String telephone,
			Preferences preferences) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.preferences = preferences;
	}
	
	public static void main(String[] args) {
		Membre m = Membre.creerMembreConsole();
		System.out.println(m.toStringLong());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return pseudo;
	}

	public String toStringLong() {
		return "Membre : "+pseudo+", nom : "+nom+", email : "+email+", telephone : "+telephone+", preferences : "+preferences;
	}

	public static Membre creerMembreConsole(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Création d'un nouveau membre.");
		System.out.print("Pseudo : ");
		String pseudo = sc.nextLine();
		System.out.print("Nom : ");
		String nom = sc.nextLine();
		System.out.print("Email : ");
		String email = sc.nextLine();
		System.out.print("Telephone : ");
		String tel = sc.nextLine();
		System.out.println("Choisissez vos préférences : ");
		Preferences prefs= Preferences.creerPrefConsole();
		
		Membre m = new Membre(pseudo, nom, email, tel, prefs);
		return m;
	}
}
