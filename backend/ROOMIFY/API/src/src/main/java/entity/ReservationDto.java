package entity;



public class ReservationDto {
	private String typeSeance;
	private String dateDebut;
	private String dateFin;
	private String nomSalle;
	private long idSalle;
	private long idUtilisateur;
	private long idFiliere;
	private long id;
	
	
	
	
	public ReservationDto() {
		super();
	}




	public String getTypeSeance() {
		return typeSeance;
	}




	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}




	public String getDateDebut() {
		return dateDebut;
	}




	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}




	public String getDateFin() {
		return dateFin;
	}




	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}




	public long getIdUtilisateur() {
		return idUtilisateur;
	}




	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}




	public long getIdFiliere() {
		return idFiliere;
	}




	public void setIdFiliere(long idFiliere) {
		this.idFiliere = idFiliere;
	}




	public long getIdSalle() {
		return idSalle;
	}




	public void setIdSalle(long idSalle) {
		this.idSalle = idSalle;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	

	public ReservationDto(String nomSalle ,String typeSeance, String dateDebut, String dateFin, long idSalle,
			long idUtilisateur, long idFiliere, long id) {
		super();
		this.nomSalle = nomSalle;
		this.typeSeance = typeSeance;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.idSalle = idSalle;
		this.idUtilisateur = idUtilisateur;
		this.idFiliere = idFiliere;
		this.id = id;
	}

	public ReservationDto(String typeSeance, String dateDebut, String dateFin, long idSalle,
			long idUtilisateur, long idFiliere, long id) {
		
		
		this.typeSeance = typeSeance;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.idSalle = idSalle;
		this.idUtilisateur = idUtilisateur;
		this.idFiliere = idFiliere;
		this.id = id;
	}



	public String getNomSalle() {
		return nomSalle;
	}




	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}


	
}