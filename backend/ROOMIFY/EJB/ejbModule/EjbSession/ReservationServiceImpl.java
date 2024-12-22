package EjbSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.*;

@Stateless

public class ReservationServiceImpl implements ReservationServiceRemote, ReservationServiceLocal {
	@PersistenceContext
    private EntityManager em;

    @Override 
    public Reservation findReservation(long id)
    {
    	return em.find(Reservation.class, id);
    }

	@Override
	public boolean addReservation(String typeSeance, Date dateDebut, Date dateFin, long idFiliere, long idSalle, long idProf) {
	    try {
	        
	        System.out.println("Recherche de la Salle avec ID: " + idSalle);
	        Salle salle = em.find(Salle.class, idSalle);
	        if (salle == null) {
	            System.out.println("Erreur: Salle non trouvée.");
	            return false;
	        }

	        System.out.println("Recherche de l'Utilisateur avec ID: " + idProf);
	        Utilisateur u = em.find(Utilisateur.class, idProf);
	        if (u == null) {
	            System.out.println("Erreur: Utilisateur non trouvé.");
	            return false;
	        }

	        System.out.println("Recherche de la Filière avec ID: " + idFiliere);
	        Filiere f = em.find(Filiere.class, idFiliere);
	        if (f == null) {
	            System.out.println("Erreur: Filière non trouvée.");
	            return false;
	        }

	        // Créer la nouvelle réservation
	        Reservation nouvelleReservation = new Reservation(dateFin, dateDebut, typeSeance, salle, f, u);

	        // Vérifier la disponibilité de la salle
	       if(salle.isDisponibilite()) {
	            em.persist(nouvelleReservation);
	            return true;}
	       else {
	    	   return false;
	       }
	       
	    } catch (Exception e) {
	        System.out.println("Erreur lors de l'ajout de la réservation : " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public List<Reservation> consulterReservationsParUtilisateur(long id_utilisateur) {
		return em.createQuery("SELECT r FROM Reservation r WHERE r.utilisateur.id = :idUtilisateur", Reservation.class)
                .setParameter("idUtilisateur", id_utilisateur)
                .getResultList();
		
	}
	@Override
	public List<Reservation> consulterToutesLesReservations() {
		 // Utilisation de l'EntityManager pour récupérer toutes les réservations
        String jpql = "SELECT r FROM Reservation r";
        return em.createQuery(jpql, Reservation.class).getResultList();
	}
	@Override
	public Salle proposerAlternative(String TypeSeance, Date dateDebut, Date dateFin) {
	    
	    List<Salle> sallesDisponibles = em.createQuery(
	            "SELECT s FROM Salle s WHERE s.disponibilite = true ", Salle.class) .getResultList();

	    if (!sallesDisponibles.isEmpty()) {
	        
	        return sallesDisponibles.get(0);
	    }

	    
	    return sallesDisponibles.isEmpty() ? null : sallesDisponibles.get(0);
	}

	 

}