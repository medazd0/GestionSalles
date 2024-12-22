package entity;

public class SalleResponse {
   private long id ; 
    private String nom;
    private String localisation;
    private int capacite;
    private String type;
    private boolean disponibilite;
	public SalleResponse( String nom, String localisation, int capacite, String type, boolean disponibilite ,long id) {
		super();
		this.id = id;
		this.nom = nom;
		this.localisation = localisation;
		this.capacite = capacite;
		this.type = type;
		this.disponibilite = disponibilite;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

}
