package EjbSession;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.*;

@Stateless
public class FiliereServiceImpl implements FiliereServiceRemote, FiliereServiceLocal {

    @PersistenceContext
    private EntityManager em;

    // Ajouter une nouvelle filière
    @Override
    public void AjouterFiliere(String nom, int effectif) {
        Filiere filiere = new Filiere();
        filiere.setNom(nom);
        filiere.setEffectif(effectif);
        em.persist(filiere); // Persist la filière dans la base de données
    }

    // Modifier une filière existante
 
    @Override
    public void ModifierFiliere(long id, String nom, int effectif) {
        Filiere filiere = em.find(Filiere.class, id);
        if (filiere != null) {
            filiere.setNom(nom);
            filiere.setEffectif(effectif);
        }
    }

    // Supprimer une filière
    @Override
    public void SupprimerFiliere(long id) {
        Filiere filiere = em.find(Filiere.class, id);
        if (filiere != null) {
            em.remove(filiere); // Supprime la filière de la base
        }
    }

    // Consulter toutes les filières
    @Override
    public List<Filiere> consulterToutesLesFilieres() {
        Query query = em.createQuery("SELECT f FROM Filiere f");
        return query.getResultList(); // Retourne la liste des filières
    }

	@Override
	public Filiere getfiliereById(Long id) {
		  
		return em.find(Filiere.class, id);
	}
}
