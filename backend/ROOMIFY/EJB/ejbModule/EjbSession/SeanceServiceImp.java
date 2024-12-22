package EjbSession;

import DTOS.seance.SeanceRequest;
import entities.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class SeanceServiceImp implements SeanceServiceLocal ,SeanceServiceRemote{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void ajouterSeance(Seance seance) {
       
        entityManager.persist(seance);
    }

    @Override
    @Transactional
    public void modifierSeance(long id, SeanceRequest seanceRequest) {
        Seance seance = entityManager.find(Seance.class, id);
        if (seance == null) {
            throw new IllegalArgumentException("Séance introuvable avec l'ID : " + id);
        }

        Filiere filiere = entityManager.find(Filiere.class, seanceRequest.getFiliereId());
        Utilisateur professeur = entityManager.find(Utilisateur.class, seanceRequest.getProfId());
        Matiere matiere = entityManager.find(Matiere.class, seanceRequest.getMatiereId());
        Salle salle = entityManager.find(Salle.class, seanceRequest.getSalleId());

        if (filiere == null || professeur == null || matiere == null || salle == null) {
            throw new IllegalArgumentException("Filière, professeur, matière ou salle introuvable(s).");
        }

       ;
        seance.setHeureDebut(seanceRequest.getHeureDebut());
        seance.setHeureFin(seanceRequest.getHeureFin());
        seance.setTypeSeance(seanceRequest.getType());
        seance.setFiliere(filiere);
        seance.setUtilisateur(professeur);
        seance.setSalle(salle);
        seance.setMatiere(matiere);

        entityManager.merge(seance);
    }

    @Override
    @Transactional
    public void supprimerSeance(long id) {
        Seance seance = entityManager.find(Seance.class, id);
        if (seance != null) {
            entityManager.remove(seance);
        }
    }

    @Override
    public Seance trouverSeance(long id) {
        return entityManager.find(Seance.class, id);
    }

    @Override
    public List<Seance> listerSeances() {
        return entityManager.createQuery("SELECT s FROM Seance s", Seance.class).getResultList();
    }

    @Override
    public List<Seance> getSeancesByFiliere(Long idFiliere) {
        String jpql = "SELECT s FROM Seance s WHERE s.filiere.id = :idFiliere";
        return entityManager.createQuery(jpql, Seance.class)
                            .setParameter("idFiliere", idFiliere)
                            .getResultList();
    }
}
