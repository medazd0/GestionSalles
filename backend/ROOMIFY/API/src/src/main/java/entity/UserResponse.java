package entity;



public class UserResponse {
	private String role;
	private long id;
	private String nom;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public UserResponse(String role, long id, String nom) {
		super();
		this.role = role;
		this.id = id;
		this.nom = nom;
	}
	
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

}
