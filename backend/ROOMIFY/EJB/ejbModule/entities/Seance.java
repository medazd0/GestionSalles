package entities;

import javax.persistence.*;

@Entity
@Table(name = "seances")
public class Seance {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     // Format: "yyyy-MM-dd"
    private String jour; // Format: "HH:mm:ss"
    private String heure; // Format: "HH:mm:ss"
    private String typeSeance;

    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    
    @ManyToOne
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    public Seance() {}

    

    public Seance( String jour, String heure, String typeSeance, Utilisateur utilisateur, Filiere filiere,
			Matiere matiere, Salle salle) {
		super();
	
		this.jour = jour;
		this.heure = heure;
		this.typeSeance = typeSeance;
		this.utilisateur = utilisateur;
		this.filiere = filiere;
		this.matiere = matiere;
		this.salle = salle;
	}



	// Getters et Setters
    

    public void setId(Long id) {
        this.id = id;
    }

   

   

    public String getHeureDebut() {
        return jour;
    }

    public void setHeureDebut(String heureDebut) {
        this.jour = heureDebut;
    }

    public String getHeureFin() {
        return heure;
    }

    public void setHeureFin(String heureFin) {
        this.heure = heureFin;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
