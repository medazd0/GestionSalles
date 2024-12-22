package EjbSession;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.*;


@Stateless
public class MatiereServiceImpl implements MatiereServiceLocal, MatiereServiceRemote  {

	@PersistenceContext
    private EntityManager em;

    // Ajouter une nouvelle matière
    @Override
    public void ajouterMatiere(String nom, int chargeHoraire , Filiere filiere) {
    	    Matiere m=new  Matiere();
    	    m.setNom(nom);
    	    m.setFiliere(filiere);
    	    m.setHeures(chargeHoraire);
    	    m.setId(0);
            em.persist(m); // Ajout dans la base	
		
    }

    // Modifier une matière existante
    @Override
    public void modifierMatiere(long id, String nouveauNom, int nouvelleChargeHoraire) {
        Matiere matiere = em.find(Matiere.class, id);
        if (matiere != null) {
            matiere.setNom(nouveauNom);
            matiere.setHeures(nouvelleChargeHoraire);
            em.merge(matiere); // Mise à jour dans la base
        }
    }

    @Override
    public void supprimerMatiere(long id) {
        Matiere matiere = em.find(Matiere.class, id);
        if (matiere == null) {
            System.out.println("Matière introuvable pour l'ID : " + id);
            throw new IllegalArgumentException("Matière introuvable pour l'ID : " + id);
        }

        System.out.println("Matière trouvée : " + matiere.getNom());

        // Supprimer la matière
        em.remove(matiere);
        System.out.println("Matière supprimée avec succès.");
    }



    // Consulter les matières par filière
    @Override
    public List<Matiere> consulterMatieresParFiliere(Filiere filiere) {
        Query query = em.createQuery("SELECT m FROM Matiere m WHERE m.filiere = :filiere");
        query.setParameter("filiere", filiere);
        return query.getResultList(); // Retourne la liste des matières associées
    }

	@Override
	public Matiere getMatiere(long id) {
		
		return em.find(Matiere.class, id);
	}
}
