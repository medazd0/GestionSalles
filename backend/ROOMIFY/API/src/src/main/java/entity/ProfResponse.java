package entity;



public class ProfResponse {
	private long id;
	private String email;
	private String nom;
	public ProfResponse(long id, String email, String nom) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ProfResponse() {
		super();
	}
	
	
	

}