package membre;

import java.util.ArrayList;

//trololo le super commentaire inutile

public class Membre {
	protected String nom;
	protected String email;
	protected String telephone;
	protected ArrayList<String> preferences;
	
	/**
	 * @param nom
	 * @param email
	 * @param telephone
	 * @param preferences
	 */
	public Membre(String nom, String email, String telephone,
			ArrayList<String> preferences) {
		super();
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.preferences = preferences;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Membre [nom=" + nom + ", email=" + email + ", telephone="
				+ telephone + ", preferences=" + preferences + "]";
	}
	
	
}
