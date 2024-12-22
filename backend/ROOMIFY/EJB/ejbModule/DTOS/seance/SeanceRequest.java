package DTOS.seance;

public class SeanceRequest {

    private String nom;
    private String date;
    private String heureDebut;
    private String heureFin;
    private long filiereId;
    private String type;
    private Long profId;
    private Long matiereId;
    private Long salleId;

    public SeanceRequest() {}

   

    public SeanceRequest(String nom, String date, String heureDebut, String heureFin, long filiereId, String type,
			Long matiereId) {
		super();
		this.nom = nom;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.filiereId = filiereId;
		this.type = type;
		this.matiereId = matiereId;
	}



	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public long getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(long filiereId) {
        this.filiereId = filiereId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProfId() {
        return profId;
    }

    public void setProfId(Long profId) {
        this.profId = profId;
    }

    public Long getMatiereId() {
        return matiereId;
    }

    public void setMatiereId(Long matiereId) {
        this.matiereId = matiereId;
    }

    public Long getSalleId() {
        return salleId;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }
}
