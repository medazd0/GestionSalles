package entity;

public class EmploiRequest {
	private String nomProf;
	private String nomSallle;
	private String nomMatiere;
	private String jour;
	private String heure;
	public String getNomProf() {
		return nomProf;
	}
	public void setNomProf(String nomProf) {
		this.nomProf = nomProf;
	}
	public String getNomSallle() {
		return nomSallle;
	}
	public void setNomSallle(String nomSallle) {
		this.nomSallle = nomSallle;
	}
	public String getNomMatiere() {
		return nomMatiere;
	}
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
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
	public EmploiRequest(String nomProf, String nomSallle, String nomMatiere, String jour, String heure) {
		super();
		this.nomProf = nomProf;
		this.nomSallle = nomSallle;
		this.nomMatiere = nomMatiere;
		this.jour = jour;
		this.heure = heure;
	}
	public EmploiRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
}
