package entity;

public class MatiereResponse {
	private String nom;
	private long id;
	private int heures ;
	public MatiereResponse(String nom, long id, int heures) {
		super();
		this.nom = nom;
		this.id = id;
		this.heures = heures;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getHeures() {
		return heures;
	}
	public void setHeures(int heures) {
		this.heures = heures;
	} 

}
