package trajet;

import java.util.ArrayList;

public class DatabaseTrajet {
protected ArrayList<Trajet> listeTrajets;
	
	public DatabaseTrajet(){
		listeTrajets = new ArrayList<Trajet>();
	}
	
	public void addTrajet(Trajet t){
		listeTrajets.add(t);
	}
}
