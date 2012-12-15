package membre;


public class Membre {
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
	public Membre(String nom, String email, String telephone,
			Preferences preferences) {
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
		return nom;
	}

	public String toStringLong() {
		return "Membre : "+nom+", email : "+email+", telephone : "+telephone+", preferences : "+preferences;
	}

}
