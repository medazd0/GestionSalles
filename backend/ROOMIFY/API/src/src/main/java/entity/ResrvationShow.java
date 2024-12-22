package entity;
public class ResrvationShow {
    private String typeSeance;
    private String dateDebut;
    private String dateFin;
    private String nomUtilisateur;  // Changement ici
    private String nomFiliere;       // Changement ici
    private String nomSalle;         // Changement ici
    private Long idReservation;

    // Constructeur (avec les noms au lieu des IDs)
    public ResrvationShow(String typeSeance, String dateDebut, String dateFin, String nomUtilisateur, String nomFiliere, String nomSalle, Long idReservation) {
        this.typeSeance = typeSeance;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomUtilisateur = nomUtilisateur;
        this.nomFiliere = nomFiliere;
        this.nomSalle = nomSalle;
        this.idReservation = idReservation;
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

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getNomFiliere() {
		return nomFiliere;
	}

	public void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public Long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}
    
    
    
    
    
    
    
    
    
}




