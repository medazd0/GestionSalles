package entity;

public class Proff {
	private String nom;
	private long id;
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
	public Proff(String nom, long id) {
		super();
		this.nom = nom;
		this.id = id;
	}
	public Proff() {
		super();
	}
	

}
