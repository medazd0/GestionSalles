package entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "matieres")
public class Matiere implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String nom;
    private int heures;

  

    public Matiere( String nom, int heures, Filiere filiere) {
		
		this.nom = nom;
		this.heures = heures;
		
		this.filiere = filiere;
	}

	@OneToMany(mappedBy = "matiere")
    private List<Seance> seances;
    
    @ManyToOne
    @JsonBackReference
    private Filiere filiere;

    
    public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Matiere() {}

    public Matiere(String nom, int heures) {
        this.nom = nom;
        this.heures = heures;
       
    }

    // Getters et Setters
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

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

   

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
