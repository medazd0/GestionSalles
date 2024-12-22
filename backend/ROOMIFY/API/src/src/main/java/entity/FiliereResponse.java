package entity;

public class FiliereResponse {

    private Long id;       // L'ID devient de type Long
    private String nom;
    private int effectif;

    // Constructeur qui accepte Long pour l'id et String pour le nom et effectif
    public FiliereResponse(Long id, String nom, int i) {
        this.id = id;
        this.nom = nom;
        this.effectif = i;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEffectif() {
        return effectif;
    }

	public void setEffectif(int effectif) {
		this.effectif = effectif;
	}


    
}
