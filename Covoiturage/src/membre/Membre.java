package membre;

import java.util.Scanner;

import covoiturage.Covoiturage;


/**Gestion des caractéristiques d'un membre du système de Covoiturage
 * @author charlerlin
 *
 */
public class Membre {
	protected String pseudo;
	protected String nom;
	protected String email;
	protected String telephone;
	protected Preferences preferences;

	/** Création d'un membre par les données passées en paramètres
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
		String pseudo = "Tintin";
		Membre m = Membre.creerMembreConsole(pseudo);
		System.out.println(m.toStringLong());
		m.editMembreConsole();
		System.out.println(m.toStringLong());
	}

	//GETTERS
	public String getPseudo() {
		return pseudo;
	}
	public String getNom() {
		return nom;
	}
	//fin des getters 
	
	/**Edition des caractéritiques du membre depuis la console
	 * Possibilité de changer ou conserver les coordonnées (ne rien entrer de nouveau et valider la saisie)
	 * Nécessité de réentrer les préférences
	 */
	public void editMembreConsole(){
		System.out.println("Édition du profil (appuyer sur Entrée pour ne pas modifier le champ, sauf pour les préférences)");
		System.out.println("Votre téléphone actuel est : "+telephone);
		Scanner sc = new Scanner(System.in);
		System.out.print("Nouveau téléphone ? ");
		String tmp = sc.nextLine();
		if(!tmp.equals(""))
			telephone = tmp;
		System.out.println("Votre email actuel est : "+email);
		System.out.print("Nouvel email ? ");
		tmp = sc.nextLine();
		if(!tmp.equals(""))
			email = tmp;
		preferences = Preferences.creerPrefConsole();

		System.out.println("Voici votre profil :\n"+toStringLong());

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nom;
	}

	/**Présentation du membre dans l'intégralité
	 * @return une string formatée avec toutes les infos sur le membre
	 */
	public String toStringLong() {
		return "Membre : "+pseudo+", nom : "+nom+", email : "+email+", telephone : "+telephone+", preferences : "+preferences;
	}

	/**Dialogue permettant de créer un membre depuis la console 
	 * en indiquant le pseudo qui doit être unique dans le système : nécéssité de vérifier avant d'appeler cette méthode
	 * 
	 * @param pseudo pseudo du membre (unicité)
	 * @return un membre créé à partir des données console
	 */
	public static Membre creerMembreConsole(String pseudo){
		Scanner sc = new Scanner(System.in);
		System.out.println("Création d'un nouveau membre.");

		String nom = Covoiturage.entreeNonVide("Nom : ");
		
		boolean coordOK = false;
		String email = ""; 
		String tel = "";
		while(!coordOK){
			System.out.print("Email : ");
			email = sc.nextLine().trim();
			System.out.print("Telephone : ");
			tel = sc.nextLine().trim();
			if(email.isEmpty() && tel.isEmpty()){
				System.out.println("Vous devez renseigner au moins une coordonnée, recommencez.");
			}
			else{
				coordOK = true;
			}
		}
		System.out.println("Choisissez vos préférences : ");
		Preferences prefs= Preferences.creerPrefConsole();

		Membre m = new Membre(pseudo, nom, email, tel, prefs);
		return m;
	}
}
