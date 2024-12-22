package entity;

public class SeanceRequestWeb {
    private long id_filiere;
    private long id_matiere;
    private long id_user;
    private long id_salle;
 
	private String jour;
    private String heure;
    private String type;
    
    public long getId_salle() {
 		return id_salle;
 	}
 	public void setId_salle(long id_salle) {
 		this.id_salle = id_salle;
 	}
	public long getId_filiere() {
		return id_filiere;
	}
	public void setId_filiere(long id_filiere) {
		this.id_filiere = id_filiere;
	}
	public long getId_matiere() {
		return id_matiere;
	}
	public void setId_matiere(long id_matiere) {
		this.id_matiere = id_matiere;
	}
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

   
}

