package DTOS.seance;

import java.io.Serializable;

public class MatiereRequest implements Serializable{
	private static final long serialVersionUID = 1L;

    private String nom;
    private int heures;
	public MatiereRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatiereRequest(String nom, int heures) {
		super();

		this.nom = nom;
		this.heures = heures;
	}
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
    
    
    
}
