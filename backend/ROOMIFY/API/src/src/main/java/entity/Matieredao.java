package entity;

public class Matieredao {
	private String nom;
	private int heures;
	private long id_filiere;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getHeures() {
		return heures;
	}
	public void setHeures(int heures) {
		this.heures = heures;
	}
	public long getId_filiere() {
		return id_filiere;
	}
	public void setId_filiere(long id_filiere) {
		this.id_filiere = id_filiere;
	}
	public Matieredao(String nom, int heures, long id_filiere) {
		
		this.nom = nom;
		this.heures = heures;
		this.id_filiere = id_filiere;
	}
	public Matieredao() {
		// TODO Auto-generated constructor stub
	}
	

}
