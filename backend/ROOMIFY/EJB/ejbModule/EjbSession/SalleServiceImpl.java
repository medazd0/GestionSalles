package EjbSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Reservation;
import entities.Salle;
@Stateless
public class SalleServiceImpl implements SalleServiceRemote, SalleServiceLocal {
	@PersistenceContext
    private EntityManager em;

    // Simule une base de données avec une liste en mémoire
    private List<Salle> salles = new ArrayList<>();
    
    private Map<Long, LocalDate> salleLiberationMap = new HashMap<>();

    @Override
    public void ajouterSalle(String nom, String localisation, int capacite, String type, boolean disponibilite) {
        Salle nouvelleSalle = new Salle(nom, localisation, capacite, type, disponibilite);
        salles.add(nouvelleSalle);
        System.out.println("Salle ajoutée : " + nouvelleSalle);
    }

    @Override
    public void modifierSalle(long id, String nom, String localisation, int capacite, String type, boolean disponibilite) {
        Salle salle = trouverSalle(id);
        if (salle != null) {
            salle.setNom(nom);
            salle.setLocalisation(localisation);
            salle.setCapacite(capacite);
            salle.setType(type);
            salle.setDisponibilite(disponibilite);
            System.out.println("Salle modifiée : " + salle);
        } else {
            System.out.println("Salle introuvable avec l'ID : " + id);
        }
    }

    @Override
    public void supprimerSalle(long id) {
        Salle salle = trouverSalle(id);
        if (salle != null) {
            salles.remove(salle);
            System.out.println("Salle supprimée : " + salle);
        } else {
            System.out.println("Salle introuvable avec l'ID : " + id);
        }
    }

    @Override
    public Salle trouverSalle(long id) {
    	return em.find(Salle.class, id);
        
    }
   @Override
	 public void reserveSalle(long id)
	 {
	   Salle salle=trouverSalle(id);
       salle.setDisponibilite(false);
       em.merge(salle);
		   
	 }
    @Override
    public List<Salle> consulterSallesDisponibles() {
        TypedQuery<Salle> query = em.createQuery("SELECT s FROM Salle s WHERE s.disponibilite = TRUE", Salle.class);
        return query.getResultList();  // Get all available salles
    }


   

 


    @Override
    public boolean verifierDisponibiliteSalle(Salle salle, LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        // Ici, ajoutez une logique pour vérifier les réservations existantes
        if (salle != null) {
            return salle.isDisponibilite(); // Exemple simplifié
        }
        return false;
    }

    @Override
    public String libererSalleDefinitivement(long idSalle) {
        Salle salle = trouverSalle(idSalle);
        if (salle != null) {
            salle.setDisponibilite(true);
            em.merge(salle);

            Reservation reservation = em.createQuery("SELECT r FROM Reservation r WHERE r.salle.id = :idSalle", Reservation.class)
                                        .setParameter("idSalle", idSalle)
                                        .getResultList().get(0);

            if (reservation != null) {
                em.remove(reservation);
            }
           
            return "Salle libérée définitivement : " + salle.getNom();
        } else {
            return "Salle introuvable avec l'ID : " + idSalle;
        }
    }

	@Override
	public String libererSalleExceptionnellement(long id, LocalDate dateFinLiberation) {
		  Salle salle = trouverSalle(id);
	        if (salle != null) {
	            // Si la date actuelle est avant la date de fin de libération
	            if (LocalDate.now().isBefore(dateFinLiberation)) {
	                salle.setDisponibilite(true);  // La salle devient disponible
	                salleLiberationMap.put(id, dateFinLiberation); // Enregistre la date de fin de libération
	                return "Salle " + salle.getNom() + " est maintenant disponible pour les réservations, jusqu a:"+dateFinLiberation;
	            } else {
	                salle.setDisponibilite(false); // La salle devient indisponible après la période
	                salleLiberationMap.put(id, LocalDate.now()); // La date de fin de libération est aujourd'hui, la salle devient indisponible
	                return "Salle " + salle.getNom() + " est maintenant indisponible après le " + dateFinLiberation;
	            }
	        } else {
	            return "Salle introuvable avec l'ID : " + id;
	        }
	}
}